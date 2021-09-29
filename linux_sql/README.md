# Introduction
- This product uses google cloud platform(Compute Engine) to initialize a virtual machine.
- Within the virtual machine we provision a docker instance.
- A postgres instance is then provisioned on docker.
- Data is inserted and retrieved from postgres which details hardware specifications.
- Some technologies used in this project were; regex, bash, docker, git, postgres, virtual machine and google cloud platform.

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
* * * * * bash /home/centos/dev/jarviscanada/jarvis_data_eng_DavidTrumble/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```


# Implemenation
Discuss how you implement the project.

## Architecture
![cluster](https://user-images.githubusercontent.com/73845528/135342282-35a0a44d-a3e4-44a3-9bed-333153e32eed.jpg)


## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh
```
As a developer, I would like to design two tables to persist hardware specifications data and resource usage data into the psql instance to perform data analytics.

As a developer, I would like to create a `ddl.sql` script will automate the database initialization to eliminate all manual process.
```
- host_info.sh
```
As a developer, I would like to write a monitoring agent program using Bash scripts. This program will be installed on each server to collect both hardware specification data and resource usage data, and then persist data into a psql instance. Therefore, the program can be installed on each server and collect data automatically.
```
- host_usage.sh
```
`host_info.sh`: The script collects hardware specification data and then insert the data to the psql instance. You can assume that hardware specifications are static, so the script will be executed only once. 

`host_usage`: The script collects server usage data and then insert the data into the psql database. The script will be executed every minute using Linux `crontab` (you don't have to deal with this in this ticket)
```
- crontab
```
should be executed every minute, so it collects data continuously.
```
- queries.sql (describe what business problem you are trying to resolve)
```
```

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
```
	id             	
     	hostname         	
     	cpu_number		
     	cpu_architecture 	
	cpu_model		
	cpu_mhz			
	L2_cache		
	total_mem		
	timestamp 
```
- `host_usage`
```
	timestamp 
	host_id
	memory_free 
	cpu_idle
	cpu_kernel 
	disk_io
	disk_available 
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
creation, automation and querying\
contrab, psql and bash
