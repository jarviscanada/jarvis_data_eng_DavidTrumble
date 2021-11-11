# Introduction
What does this app do?
Technologies include: IntelliJ IDE, Twitter REST API, HTTP client, Maven, Java, Docker, Junit and Mockito.

# Quick Start
- how to package your app using mvn?
- how to run your app with docker?

# Design
## UML diagram
## explain each component(app/main, controller, service, DAO) (30-50 words each)
## Models
Talk about tweet model
## Spring
- How you managed the dependencies using Spring?

# Test
How did you test you app using Junit and mockito?

## Deployment
Steps to dockerize app:

```
docker_user=docker_id
docker login
mvn clean package
docker build -t ${docker_user}/twitter .
docker push ${docker_user}/twitter
```

# Improvements
- Imporvement 1
- Imporvement 2
- Imporvement 3
