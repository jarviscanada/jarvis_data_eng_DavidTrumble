# Introduction
This project uses JDBC to establish a connection between java Application and a Postgres database running on a docker container using postgres image to implement CRUD operations on sales order information using DAO patterns. The DAO connects to a local database and performs: creation, reading, updating and deleting through CRUD operations. This project uses Maven Standart Directory Layout. The application uses a psql instance to create, populate and query a database. Also, this project populates an ER diagram with DBeaver. JDBC is used to handle errors and act as connection driver to the psql database. The entirety of the application is allocated on a Docker image. 

# Implementaiton
## ER Diagram
ER diagram

## Design Patterns
Discuss DAO and Repository design patterns (150-200 words)
The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application/business layer from the persistence layer (usually a relational database, but it could be any other persistence mechanism) using an abstract API.

The functionality of this API is to hide from the application all the complexities involved in performing CRUD operations.
This permits both layers to evolve separately without knowing anything about each other

From a bird's eye view, it's clear to see that the Dao interface defines an abstract API that performs CRUD operations on objects of type T.
Due to the high level of abstraction that the interface provides, it's easy to create a concrete, fine-grained implementation that works with User objects.
first it is an abstraction of the data layer and second it is a way of centralising the handling of the domain objects.

# Test
A psql server and database was created in tandem with a Docker image on a Maven project to host relevant SQL and RDBMS data. Using JDBC to connect to the psql instance, the application DAO class files were tested to demonstrate CRUD operations. This was accomplished by querying the new RDBMS data using the psql command line tool.
