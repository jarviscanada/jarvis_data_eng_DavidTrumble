# Introduction
What does this app do?
Technologies include: IntelliJ IDE, Twitter REST API, HTTP client, Maven, Java, Docker, Junit and Mockito.

# Quick Start
- how to package your app using mvn?
- how to run your app with docker?

# Design
## UML diagram
![twitter uml](../../../../twitterAppUML2.png)

## App/Main
## Controller 
## Service 
## DAO
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
- Create document of available tweets to post.
- Automate post script to tweet every 24 hours.
- Automate post script via Google hosting service api gateway.
