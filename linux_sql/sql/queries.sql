-- author: david trumble
-- file: queries.sql


-- TODO - GROUP HOSTS BY HARDWARE INFO
-- 	group hosts by cpu number 
-- 	sort by their memory size in descending order(w/in each cpu_number group)

-- 		sample output
-- 		cpu_number,host_id,total_mem

-- 		1,1,2048
-- 		1,5,1568
-- 		1,9,1024
-- 		2,4,4088
-- 		2,6,1024


-- SELECT cpu_number, host_id, total_mem
-- FROM host_info




-- TODO - AVERAGE MEMORY USAGE

-- 	average used memory in in percentage
--	over 5 mins interval for each host
--	user memory = total memory - free memory


-- 		Sample query output
-- 		host_id, host_name, timestamp,avg_used_mem_percentage

-- 		1,node1.jrvs.ca,2019-01-01 00:00:00,97
-- 		1,node1.jrvs.ca,2019-01-01 00:05:00,90
-- 		1,node1.jrvs.ca,2019-01-01 00:10:00,65
-- 		2,node1.jrvs.ca,2019-01-01 00:00:00,50
-- 		2,node1.jrvs.ca,2019-01-01 00:05:00,40
-- 		2,node1.jrvs.ca,2019-01-01 00:10:00,30


-- SELECT host_id, host_name, timestamp, total_mem- free_mem AS 
-- FROM host_info



-- TODO DETECT HOST FAILURE

-- We can assume that a server is failed when it inserts less than three data points within 5-min interval.
-- Please write a query to detect host failures.


-- 		host_id, timestamp, num_data_points
--		COUNT DATA POINTS IN 5 MINUTES INTERVALS?

--		q is similar to previous one which requires to round timestamp o the nearest 5-minute interval.
