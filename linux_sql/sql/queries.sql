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
	(select hostname from host_info), 
        date_trunc('hour', timestamp) + date_part('minute', timestamp)::int / 5 * interval '5 min' AS timestamp,
	AVG(100 * ((SELECT total_mem from host_info) - memory_free) / (SELECT total_mem from host_info) AS "avg_used_mem_percentage"
FROM 
	host_usage
GROUP BY
	host_id,
	hostname,
	timestamp
ORDER BY
	host_id,
	timestamp;	

-- TODO DETECT HOST FAILURE
SELECT 
	host_id, 
	date_trunc('hour', timestamp) + date_part('minute', timestamp)::int / 5 * interval '5 min' AS timestamp,
	COUNT(timestamp) AS num_data_points 
FROM
	host_usage
GROUP BY
	host_id,
	timestamp,
	num_data_points
	
ORDER BY 
	host_id,
	timestamp;
