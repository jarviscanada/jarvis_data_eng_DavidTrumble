Table of contents
* [Introduction](#Introduction)
* ["Hadoop Cluster"](#Hadoop Cluster)
* ["Hive Project"](#Hive Project)
* [Improvements](#Improvements)

# Introduction
The purpose of this project was to perform data analytics and process big data using Apache Hadoop and other tools such as: HDFS, YARN, Zeppelin, Hive and Spark. This project required the provisioning of a three node Hadoop cluster with GCP Dataproc service. Then a Dataset was prepared from source: Google Public Data (hosted on Google BigQuery). The Dataset was exported via the BigQuery Table to Google Storage. From which GS Data using Hive was created/ queried and Zeppelin was used to execute code. Lastly, integrating GS data to HDFS as text; as well as, continuously improving parsing and performance through the use of OpenCSVSerDe, Partition and Columnar(parquet).


# Hadoop Cluster
- cluster architecture diagram
  - 1 master and 2 workers nodes
  - HDFS, YARN, Zeppelin, Hive (hive Server, hive metastore, RDBMS), etc.
- Big data tools you evaluated (e.g. MapReduce, YARN, HDFS, Hive, Zeppelin, etc..)
- hardware specifications

# Hive Project
- Discuss how you optimized Hive queries? (e.g. partitions, columnar, etc..)
- Post your Zeppelin Notebook screenshot here
	- Make sure your Notebook is nice and clean as hiring managers will visit your project
	- use `Full Page Screen Capture` chrome extension to capture a webpage as a picture

# Improvements
- at least three improvements
