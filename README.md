 Spring Boot Application - API Interaction on Startup

This is a Spring Boot application that interacts with a remote API automatically at application startup without any manual HTTP trigger. The application performs the following steps:
1. On startup, it makes a `POST` request to the `/generateWebhook` endpoint.
2. It solves an assigned problem based on the response data.
3. It sends the result to the provided webhook URL with JWT authentication.

 Problem Overview
 Objectives
- The application must interact with a remote API on startup and make an automatic `POST` request to the generate Webhook` endpoint.
- After making the request, the application should solve a specific problem based on the response (either mutual followers or nth-level followers) and store the result in a JSON format.
- Finally, the application should send the result to the provided webhook URL with JWT authentication.

 Key Features of the Application
 1. Automatic API Interaction

At the moment of application startup, the app automatically makes a `POST` request to the remote API:

text
POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook

REUEST BODY
{
  "name": "John Doe",
  "regNo": "REG12347",
  "email": "john@example.com"
}

INPUT
{
  "users": [
    {"id": 1, "name": "Alice", "follows": [2, 3]},
    {"id": 2, "name": "Bob", "follows": [1]},
    {"id": 3, "name": "Charlie", "follows": [4]},
    {"id": 4, "name": "David", "follows": [3]}
  ]
}
OUTPUT
{
  "regNo": "REG12347",
  "outcome": [[1, 2], [3, 4]]
}

If you have any questions or suggestions, feel free to open an issue or reach out to me at:
•	Email: KSOOD2004@GMAIL.COM // KS6102@SRMIST.EDU.IN
