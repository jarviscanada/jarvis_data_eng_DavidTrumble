--


-- 1. (optional) switch to `host_agent`
\c host_agent;


-- DROP EXISTING TABLES IF EXIST
-- DROP TABLE IF EXISTS host_info, host_usage CASCADE;




-- 2. CREATE HOST_INFO TABLE IF IT DOES NOT EXIST
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



-- 3. CREATE HOST_USAGE TABLE IF IT DOES NOT EXIST
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



-- SELECT * FROM host_info;
-- SELECT * FROM host_usage;
