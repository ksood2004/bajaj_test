package com.bajaj.webhookapp.model;

import lombok.Data;
import java.util.List;

@Data
public class WebhookResponse {
    private String webhook;
    private String accessToken;
    private InputData data;

    @Data
    public static class InputData {
        private List<User> users;
        private Integer n; // for Question 2
        private Integer findId; // for Question 2
    }
}
