--
--
--

-- # ddl.sql pseudocode/steps
-- you can assume database is already created for you
-- 1. (optional) switch to `host_agent`
-- 2. create `host_info` table if not exist
-- 3. create `host_usage` table if not exist

-- # Execute ddl.sql script on the host_agent database againse the psql instance
-- psql -h localhost -U postgres -d host_agent -f sql/ddl.sql



-- sample values   -- all fields are required (NOT NULL)
id=1      #auto-increment
hostname=spry-framework-236416.internal  #fully qualified hostname
cpu_number=1
cpu_architecture=x86_64
cpu_model=Intel(R) Xeon(R) CPU @ 2.30GHz
cpu_mhz=2300.000
L2_cache=256     #in kB
total_mem=601324 #in kB
timestamp=2019-05-29 17:49:53 #Current time in UTC time zone


-- 2. CREATE HOST_INFO TABLE IF IT DOES NOT EXIST
-- DDL
CREATE TABLE PUBLIC.host_info 
  ( 
	id               	SERIAL NOT NULL, 
     	hostname         	VARCHAR NOT NULL, 
     	cpu_number		DATATYPE NOT NULL,
     	cpu_architecture 	VARCHAR NOT NULL,
	cpu_model		VARCHAR NOT NULL,
	cpu_mhz			NOT NULL,
	L2_cache		NOT NULL,
	total_mem		NOT NULL,
	timestamp 		NOT NULL,

     -- primary key constraint
     -- unique hostname constraint
  );

-- DML -- INSERT statement
INSERT INTO host_info (id, hostname ...







-- sample values -- all fields are required (NOT NULL)
timestamp=2019-05-29 16:53:28 #UTC time zone
host_id=1                     #host id from `hosts` table
memory_free= 256              #in MB
cpu_idle=95                   #in percentage
cpu_kernel=0                  #in percentage
disk_io=0                     #number of disk I/O
disk_available=31220          #in MB. root directory avaiable disk



-- 3. CREATE HOST_USAGE TABLE IF IT DOES NOT EXIST
-- DDL
CREATE TABLE PUBLIC.host_usage 
  ( 
     	"timestamp"    		TIMESTAMP NOT NULL, 
     	host_id        		SERIAL NOT NULL,
	memory_free		NOT NULL,
	cpu_idle		NOT NULL,
	cpu_kernel		NOT NULL,
	disk_io			NOT NULL,
	disk_available 		NOT NULL,

     -- add foreign key constraint
  ); 


-- DML
-- INSERT statement
INSERT INTO host_usage ("timestamp", host_id, ...
