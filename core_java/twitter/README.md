# Introduction
The Java application uses a bottom-up approach that can CRUD(create, read and delete) tweets from the CLI via the Twitter REST API. A Java library is also implemented to process the Twitter API; as the Twitter API returns data in the form of JSON. Junit and Mockito were used as testing tools, IntelliJ was used as a IDE and source code control was performed through git. Also, Maven was used to package and manage dependencies in conjuction with Spring, then the application was Dockerized and pushed to Docker Hub for easier user consumption.

# Quick Start
Package Maven
```
mvn clean
mvn package -Dmaven.test.skip
```

Run Application with Docker
```
docker pull dttrumble/twitter
```
```
docker run --rm \
-e consumerKey=YOUR_VALUE \
-e consumerSecret=YOUR_VALUE \
-e accessToken=YOUR_VALUE \
-e tokenSecret=YOUR_VALUE \
dttrumble/twitter post|get|delete [options]
```
# Design
## UML diagram

![twitter uml](assets/twitterUML.png)
## Bottom-up Approach 
```
DAO → Service → Controller → AppMain
```
## App/Main
CLI Application
- Performs HTTP request and processes the ability for user input.
- This component initializes all components and their dependencies.
## Controller 
Controller Layer
- This layer interacts and parsers the user input CLI arguments.
- Also, it calls the service layer and returns results.
## Service 
Service Layer
- The service layer handles business logic. 
- Service calls DAO layer in order to interact with storage/service. 
- Twitter REST API in this application represents the storage/service.
## DAO
Data Access Object or Data Access Layer (DAO/DAL)
- The data access layer handles models. 
- The DAO layer saves, shows and deletes tweet(s).
## Models
Tweet: Simplified version of the Twitter Tweet model
Entities: Contains Hashtag and UserMention
Hashtag: Shows hashtag text in a posted tweet
UserMention: Shows users that have been mentioned in a posted tweet
Coordinates: A list containing longitude and latitude coordinates representing the location of the tweet
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
