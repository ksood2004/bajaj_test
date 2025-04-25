package com.bajaj.webhookapp.service;

import com.bajaj.webhookapp.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String regNo = "REG12347";

    @PostConstruct
    public void start() {
        try {
            String initUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook";
            Map<String, String> request = Map.of(
                    "name", "John Doe",
                    "regNo", regNo,
                    "email", "john@example.com"
            );

            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(initUrl, request, WebhookResponse.class);
            WebhookResponse data = response.getBody();

            if (data == null) return;

            String token = data.getAccessToken();
            String webhookUrl = data.getWebhook();

            Map<String, Object> result = Map.of(
                    "regNo", regNo,
                    "outcome", solve(data)
            );

            sendWithRetry(webhookUrl, token, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<List<Integer>> solve(WebhookResponse res) {
        List<User> users = res.getData().getUsers();
        int lastDigit = Character.getNumericValue(regNo.charAt(regNo.length() - 1));

        if (lastDigit % 2 == 1) return mutualFollowers(users);
        return nthLevelFollowers(res.getData().getN(), res.getData().getFindId(), users);
    }

    private List<List<Integer>> mutualFollowers(List<User> users) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (User u : users)
            map.put(u.getId(), new HashSet<>(u.getFollows()));

        for (User u : users) {
            int id = u.getId();
            for (int f : u.getFollows()) {
                if (map.containsKey(f) && map.get(f).contains(id) && id < f)
                    result.add(List.of(id, f));
            }
        }

        return result;
    }

    private List<Integer> nthLevelFollowers(int n, int findId, List<User> users) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (User u : users)
            graph.put(u.getId(), u.getFollows());

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(findId);
        visited.add(findId);

        while (n-- > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                List<Integer> neighbors = graph.getOrDefault(current, List.of());
                for (int next : neighbors) {
                    if (!visited.contains(next)) queue.offer(next);
                    visited.add(next);
                }
            }
        }

        return new ArrayList<>(queue);
    }

    private void sendWithRetry(String url, String token, Map<String, Object> payload) {
        for (int i = 1; i <= 4; i++) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", token);

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
                restTemplate.postForEntity(url, entity, String.class);
                System.out.println("Successfully posted on attempt " + i);
                return;
            } catch (Exception e) {
                System.out.println("Attempt " + i + " failed. Retrying...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}
