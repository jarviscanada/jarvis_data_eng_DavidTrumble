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
SELECT 
	host_id, 
	(select hostname from 	host_info), 
	timestamp, 
	(((select total_mem from host_info) - memory_free) / (select total_mem from host_info)::float *100)::int AS avg_used_mem_percentage 
FROM 
	host_usage;

-- TODO DETECT HOST FAILURE
SELECT 
	host_id, 
	date_trunc('hour', timestamp) + date_part('minute', timestamp)::int / 5 * interval '5 min' AS timestamp 
FROM
	host_usage 
ORDER BY 
	timestamp, 
	host_id;
