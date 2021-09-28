-- author: david trumble
-- file: queries.sql


-- TODO - GROUP HOSTS BY HARDWARE INFO
SELECT 
	cpu_number, host_id, total_mem 
FROM 
	host_info 
ORDER_BY
	cpu_number, total_mem DESC;



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
