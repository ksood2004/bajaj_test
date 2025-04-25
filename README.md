Here is the Tech Stack section :-:

 Tech Stack

This project is built using the following technologies:

 1. Spring Boot
   - Description: A Java-based framework that simplifies the development of production-grade applications. It provides an embedded server, automatic configuration, and easy-to-use features for building REST APIs and microservices.
   - Version Used: Spring Boot 2.x
   
 2. Java
   - Description: A high-level, class-based, object-oriented programming language. It's widely used for building robust backend applications.
   - Version Used: Java 8 or higher.

 3. Maven
   - Description: A build automation tool used for managing dependencies, building the project, and running tests in Java-based projects. It helps in managing the project's lifecycle.
   - Version Used: Maven 3.x

 4. Spring Web (RestTemplate/WebClient)
   - Description: Part of the Spring framework, used for making HTTP requests. In this project, `RestTemplate` or `WebClient` is used for interacting with the remote API on application startup.
   - Version Used: Spring Web 5.x
   
 5. JWT (JSON Web Tokens)
   - Description: A compact, URL-safe means of representing claims to be transferred between two parties. JWT is used to authenticate and authorize requests to the webhook URL.
   - Version Used: jjwt (Java JWT Library)

 6. JSON
   - Description: JavaScript Object Notation (JSON) is the format used for exchanging data between the application and the remote API. It's also used for structuring the request and response bodies.
   - Version Used: Jackson (Jackson JSON processor library)



   

   - 
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
