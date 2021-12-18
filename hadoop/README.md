Table of contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop Cluster)
* [Hive Project](#Hive Project)
* [Improvements](#Improvements)


# Introduction
The purpose of this project was to perform data analytics and process big data using Apache Hadoop and other tools such as: HDFS, YARN, Zeppelin, Hive and Spark. This project required the provisioning of a three node Hadoop cluster with GCP Dataproc service. Then a Dataset was prepared from source: Google Public Data (hosted on Google BigQuery). The Dataset was exported via the BigQuery Table to Google Storage. From which GS Data using Hive was created/ queried and Zeppelin was used to execute code. Lastly, integrating GS data to HDFS as text; as well as, continuously improving parsing and performance through the use of OpenCSVSerDe, Partition and Columnar(parquet).


# Hadoop Cluster


Cluster Architecture Diagram


Master Worker, Master-Slave or the Map-Reduce pattern.

Used for parallel processing and allows applications to perform simultaneous processing across multiple machines or processes via a Master and multiple Workers. In this instance a three node Hadoop cluster is provisioned with GCP Dataproc service.


Master Node Hardware Specs
    1 Node: [ 100GB Storage - 13 GB Memory - x2 CPU Core ]


Worker Node Hardware Specs
    2 Nodes: [ 100GB Storage - 13 GB Memory - x2 CPU Core ]



MapReduce

MapReduce is a framework and processing component for Apache Hadoop that enables scalability across servers in a Hadoop cluster. 
MapReduce refers to 2 separate tasks that Hadoop programs perform: Map Job and Reduce Job. 
Map Job: takes a set of data and converts it into another set of data in which elements are put into key/value pairs (tuples).
Reduce Job: takes the output from a map as input and combines those data pairs/ tuples into a smaller set of pairs/ tuples. 


Hadoop Distributed File System - (HDFS)
HDFS is used to scale a single Apache Hadoop cluster into nodes and handles large data sets running commodity hardware.


Yet Another Resource Negotiator - (YARN) 
Apache Hadoop is a large-scale, distributed OS for big data applications; which is capable of decoupling MapReduce's resource management and scheduling capabilities from the data processing component.


Zeppelin
Apache Zeppelin a web-based notebook that offers interactive data analytics and visualization.


Hive and Hive-QL
Apache Hive is a data warehouse software project built on top of Apache Hadoop for providing data query and analysis. 
Hive gives an SQL-like interface to query data stored in various databases and file systems that integrate with Hadoop.



# Hive Project

Partitioning Optimization

Using partitioning enabled the ability to query a portion of the data; it provided a way of dividing a table into related parts based on the values of partitioned columns such as country, indicatorcode and indicatorvalue. 


Columnar Optimization

columnar database stores data by columns rather than by rows, which makes it suitable for analytical query processing. 
The reason for this being that data access patterns mostly involve selecting a few columns to perform aggregations, then using columnar storage will save disk space, reduce I/O when fetching data and improve query execution time.




# Improvements
- at least three improvements
