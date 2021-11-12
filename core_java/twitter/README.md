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
Twitter Model</br>
- Tweets are known as “status updates”. 
- The Tweet object has a long list of ‘root-level’ attributes that can also be the ‘parent’ object to several child objects. 
- The JSON will be a mix of ‘root-level’ attributes and child objects (which are represented with the {} notation.
```
{
  "created_at" : "Fri Jun 26 17:32:16 +0000 2020",
  "id" : 1276568976764686343,
  "id_str" : "1276568976764686343",
  "text" : "test post",
  "entities" : {
    "hashtags" : [ ],
    "user_mentions" : [ ]
  },
  "coordinates" : {
    "coordinates" : [ 79.0, 43.0 ],
    "type" : "Point"
  },
  "retweet_count" : 0,
  "favorite_count" : 0,
  "favorited" : false,
  "retweeted" : false
}
```

Coordinates</br>
- Represents the geographic location of this Tweet as reported by the user or client application. 
- The inner coordinates array is formatted as geoJSON (longitude first, then latitude).


Entities</br>
- The entities object is a holder of arrays of other entity sub-objects such as hashtag and user_mentions.
- hashtag: Represents hashtags which have been parsed out of the Tweet text.
- user_mentions: Represents other Twitter users mentioned in the text of the Tweet.


## Spring
The dependencies of this application were managed using the Spring framework. Jaca Class annotation were used to signify Beans(created by 'main' method). Dependency injection processes were performed in which the objects define their dependencies only through constructor arguments. 
```
    @Component: TwitterAppCLI, TwitterHttpHelper
    @Controller: TwitterController
    @Service: TwitterService
    @Repository: TwitterDAO
```
# Test
Integration Testing</br>
- JUnit annotations were used to @Test: classes method execution order, expected result and actual result. 


Unit Testing</br>
- Mockito framework was applied to test classes. 
- Mock/fake/dummy objects were created and tested by means of the dummy object return values.

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
