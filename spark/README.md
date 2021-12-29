Table of Contents

* [Introduction](#introduction)
* [Databricks and Hadoop Implementation](#databricks-and-hadoop-implementation)
* [Zeppelin and Hadoop Implementation](#zeppelin-and-hadoop-implementation)
* [Future Improvement](#future-improvement)


# Introduction
The purpose of this project is to use Data Analytics and Wrangling with Scala, Spark and Pyspark to evaluate and provide retail data strategies to business and marketing teams alike. The data was provisioned using a Master-Worker Cluster in tandem with Hadoop/ Google Cloud Platform and Azure Databricks/ Pyspark to initialize a zeppelin/ databricks notebook. Technologies include: Scala, Apache Spark, Apache Hadoop Pyspark, Microsoft Azure & Azure Databricks, Google Cloud Platform, Hadoop and Zeppelin.


# Databricks and Hadoop Implementation


## Dataset

Dataset and Notebook


```
Invoice
StockCode
Description
Quantity
Invoice Date
Price
Customer ID
Country
```
1. Total Invoice Amount Distribution (Invoice Amount/ Remove Outliers)
2. Monthly Placed and Canceled Orders
3. Monthly Sales
4. Monthly Sales Growth
5. Monthly Active Users
6. New and Existing Users
7. Finding RFM
8. RFM Segmentation



## Analytics Work
![retailDataAnalytics](assets/Retail_Data_Analytics_ScreenCapture.png)


## Architecture

Microsoft Azure with Azure Databricks was integrated with Azure services to unify this projects analytics workloads. A cluster with a Master-Worker Relationship was used to Compute, Data was used to create a dataframe via a csv data file and Notebook was used to analyze the data file.

![azureCluster](assets/azureCluster.png)

## Architecture Diagram
![dataAnalytics](assets/dataAnalytics.png)


# Zeppelin and Hadoop Implementation


## Dataset

Dataset and Notebook

```
year			
countryname		
countrycode		
indicatorname		
indicatorcode		
indicatorvalue		
```
1. Show Historical GDP for Canada
2. Show GDP for Each Country and Sort By Year
3. Find the Highest GDP for Each Country


## Analytics Work
![WDIdataAnalytics](assets/WDI_Data_Analytics.png)


## Architecture
This aspect of the project was instantiated from a previously completed #Hadoop Project where; the purpose was to perform data analytics and process big data using Apache Hadoop and other tools such as: HDFS, YARN, Zeppelin, Hive and Spark. This project required the provisioning of a three node Hadoop cluster with GCP Dataproc service. Then a Dataset was prepared from source: Google Public Data (hosted on Google BigQuery). The Dataset was exported via the BigQuery Table to Google Storage. From which GS Data using Hive was created/ queried and Zeppelin was used to execute code. Lastly, integrating GS data to HDFS as text; as well as, continuously improving parsing and performance through the use of OpenCSVSerDe, Partition and Columnar(parquet).

```
DROP TABLE IF EXISTS wdi_csv_parquet;
CREATE EXTERNAL TABLE wdi_csv_parquet
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
STORED AS PARQUET
LOCATION 'hdfs:///user/zeppelin/wdi_csv_parquet';
```

## Architecture Diagram
![hadoopDiagram](assets/hadoopDiagram.png)


# Future Improvement
- Create sample data and dataframe from a portion of existing data.
- Create views of analytical queries to lower disk usage.
- Improve the overall hardware specs of spark cluster.
