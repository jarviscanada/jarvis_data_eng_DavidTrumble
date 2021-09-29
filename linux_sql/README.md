# Introduction
- This product uses google cloud platform(Compute Engine) to initialize a virtual machine.
- Within the virtual machine we provision a docker instance.
- A postgres instance is then provisioned on docker.
- Data is inserted and retrieved from postgres which details hardware specifications.
- Technologies used in this project were; regex, bash, docker, git, postgres, centos, xfce, virtual machine and google cloud platform.

# Quick Start
- Start a psql instance using psql_docker.sh
```
./psql_docker.sh start stop create
```
- Create tables using ddl.sql
```
CREATE TABLE IF NOT EXISTS PUBLIC.host_info 
  ( 
	id               	SERIAL NOT NULl, 
     	hostname         	VARCHAR NOT NULL, 
     	cpu_number		INTEGER NOT NULL,
     	cpu_architecture 	VARCHAR NOT NULL,
	cpu_model		VARCHAR NOT NULL,
	cpu_mhz			FLOAT(3) NOT NULL,
	L2_cache		INTEGER NOT NULL,
	total_mem		INTEGER NOT NULL,
	"timestamp" 		TIMESTAMP NOT NULL,

	PRIMARY KEY (id),
	UNIQUE(hostname)
  );

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage 
  ( 
     	"timestamp"    		TIMESTAMP NOT NULL, 
     	host_id        		SERIAL NOT NULL,
	memory_free		INTEGER NOT NULL,
	cpu_idle		INTEGER NOT NULL,
	cpu_kernel		INTEGER NOT NULL,
	disk_io			INTEGER NOT NULL,
	disk_available 		INTEGER NOT NULL,

	CONSTRAINT fk_host_id FOREIGN KEY(host_id) REFERENCES host_info(id)
  ); 
  ```

- Insert hardware specs data into the DB using host_info.sh
```
insert_stmt="INSERT INTO host_info(hostname, 
				cpu_number, 
				cpu_architecture, 
				cpu_model, 
				cpu_mhz, 
				L2_cache, 
				total_mem, 
				timestamp) 
			VALUES('$hostname', 
				'$cpu_number', 
				'$cpu_architecture', 
				'$cpu_model', 
				'$cpu_mhz', 
				'$L2_cache', 
				'$total_mem', 
				'$timestamp')";
```

- Insert hardware usage data into the DB using host_usage.sh
```
insert_stmt="INSERT INTO host_usage(timestamp, 
				    host_id, 
				    memory_free, 
				    cpu_idle, 
				    cpu_kernel, 
				    disk_io, 
				    disk_available) 
			VALUES('$timestamp', 
				    (SELECT id FROM host_info WHERE hostname='$hostname'), 
				    '$memory_free', 
				    '$cpu_idle', 
				    '$cpu_kernel', 
				    '$disk_io', 
				    '$disk_available')";
```

- Crontab setup
```
# edit crontab jobs
bash> crontab -e

# set to every minute
* * * * * bash ~/dev/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```


# Implemenation

## Architecture
![cluster](https://user-images.githubusercontent.com/73845528/135342282-35a0a44d-a3e4-44a3-9bed-333153e32eed.jpg)

## Scripts
#### psql_docker.sh
- Creates data tables that persist host machine information into a psql instance for data analytics in performance.

#### host_info.sh
- Inserts the data collected from curront hardware specification data into a psql database. 

#### host_usage.sh
- Inserts the data collected from current server usage into a psql database. 

#### crontab
- Automates the database initialization and eliminates all manual processes; set to be executed every minute.

#### queries.sql (describe what business problem you are trying to resolve)
- Designed to assist in: planning for future resources, recognize server failures and monitor memory uses over various processes.

## Database Modeling
#### `host_info`
- Collects hardware specification data
```
PK	id             	
     	hostname         	
     	cpu_number		
     	cpu_architecture 	
	cpu_model		
	cpu_mhz			
	L2_cache	
	total_mem		
	timestamp 
```
#### `host_usage`
- Collects server usage data
```
PK	timestamp	
FK	host_id	
	memory_free  
	cpu_idle
	cpu_kernel 
	disk_io
	disk_available: remaining space available on a disk. 
```

# Test
- Testing docker with CLI tool.
```
./psql_docker.sh create start stop
```
- Testing psql with CLI and psql CLI tool.
```
# connect to the psql instance
psql -h localhost -U postgres -W

# list all database
postgres=# \l

# create a database
postgres=# CREATE DATABASE host_agent;

# connect to the new database;
postgres=# \c host_agent;
```

- Testing ddl(data definition language) with psql CLI tool.
```
# connect to the psql instance
psql -h localhost -U postgres -W

# connect to database;
postgres=# \c host_agent;

# display tables;
postgres=# \c host_agent;
```
- Testing dbm(database management) scripts
```
# execute scripts
scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```

- Testing psql Queries with psql CLI tool.
```
# connect to the psql instance
psql -h localhost -U postgres -W

# connect to database;
postgres=# \c host_agent;

#select table data
SELECT * FROM host_info;
SELECT * FROM host_usage;
```


# Deployment
- Deployed project through Github for source code control.
- Provisioned a docker container and postgres instance for data management.
- Executed date/time operations with chrontab to automate data updates.


# Improvements
- Google Cloud and Virtual Machine\
this and that this and that

- Docker\
this and that this and that

- Date and Timestamp\ 
creation, automation and querying
