# Introduction
(50-100 words)
What does this app do? What technoglies your have used? (JDBC, PSQL, MVN, etc..)
JDBC allows a connection between a Java application and an RDBMS. 
 JDBC and data access-designed patterns
 DBeaver client
 IDE: IntelliJ
 Docker
 PostgreSQL and psql CLI tool
 ou will use the provided skeleton code, DDL, and script, and only commit your implementation into the GitHub repo
 veloper, I want to learn basic JDBC and set up a Maven project to practice JDBC by developing a Java projec
 CRUD is a very common data access pattern. It's an interface, so it can be applied to other data sources, such as file systems, NoSQL, REST APIs, etc
 exception handling in jdbc
 rdbms
 
 This project uses JDBC to establish a connection between java Application and a Postgres database running on a docker container using postgres image to implement CRUD operations on sales order information using DAO patterns.
 
 This program implements Java Database Connectivity (JDBC) to interface with a PostgreSQL database using the built-in SQL libraries in Java 8. A database access object class connects to a local database to perform basic create, read, update and delete (CRUD) operations on the database.
 
 Use psql_docker.sh and create another database for this project
 
 Generate ER diagram 
 
 add the following JDBC driver to ./jdbc/pom.xml
 
exception handling in jdbc

# Implementaiton
## ER Diagram
ER diagram

## Design Patterns
Discuss DAO and Repository design patterns (150-200 words)
The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application/business layer from the persistence layer (usually a relational database, but it could be any other persistence mechanism) using an abstract API.

The functionality of this API is to hide from the application all the complexities involved in performing CRUD operations in the underlying storage mechanism. This permits both layers to evolve separately without knowing anything about each other

From a bird's eye view, it's clear to see that the Dao interface defines an abstract API that performs CRUD operations on objects of type T.

Due to the high level of abstraction that the interface provides, it's easy to create a concrete, fine-grained implementation that works with User objects.

first it is an abstraction of the data layer and second it is a way of centralising the handling of the domain objects.

Abstraction

The idea with this pattern is to have a generic abstract way for the app to work with the data layer without being bother with if the implementation is towards a local database or towards an online API.

# Test
How you test your app against the database? 
(e.g. database setup, test data set up, query result)

The database was created by pulling a PostgreSQL docker image and using it to create a database and tables from a set of provided .sql files. A connection manager initializes the connection to the database and a JDBCExecutor class file utilizes methods implemented in a DAO class file to demonstrate CRUD operations. The results of these operations can be verified using the psql command line tool to directly read from the database, or through a GUI-based program such as DBeaver.

The PostgreSQL server created using Docker container was populated with the sample data using the sql scripts. Using JDBCExecutor.java, a connection was established with Postgres Server. The application uses CustomerDAO and OrderDAO to do CRUD operations using the sample data created using Customer and Order objects.
