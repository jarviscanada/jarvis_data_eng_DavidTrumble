# Introduction
This product uses google cloud platform(Compute Engine) to initialize a virtual machine. 
	Within the virtual machine we provision a docker instance.
	The docker instance is then provisioned with postgres and psql data.
	Data inserted and retrieved from postgres details hardware specifications for that instance. 

Some technologies used in this project were; regex, bash, docker, git, postgres, virtual machine and google cloud platform.

# Quick Start
Use markdown code block for your quick-start commands
- Start a psql instance using psql_docker.sh
./psql_docker.sh start stop create

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
```
-- 3. CREATE HOST_USAGE TABLE IF IT DOES NOT EXIST
```
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage 
  ( 
     	"timestamp"    		TIMESTAMP NOT NULL, 
     	host_id        		SERIAL NOT NULL,
	memory_free		INTEGER NOT NULL,
	cpu_idle		INTEGER NOT NULL,
	cpu_kernel		INTEGER NOT NULL,
	disk_io			INTEGER NOT NULL,
	disk_available 		INTEGER NOT NULL,

     -- add foreign key constraint
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
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website). Image must be saved to the `assets` directory.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh


- host_info.sh


- host_usage.sh


- crontab


- queries.sql (describe what business problem you are trying to resolve)

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`


- `host_usage`


# Test
How did you test your bash scripts and SQL queries? What was the result?


# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)


# Improvements
Write at least three things you want to improve 
e.g. 
- handle hardware update 
- blah
- blah
