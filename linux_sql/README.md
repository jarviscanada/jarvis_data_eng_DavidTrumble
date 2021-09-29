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


- Insert hardware specs data into the DB using host_info.sh


- Insert hardware usage data into the DB using host_usage.sh


- Crontab setup



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
