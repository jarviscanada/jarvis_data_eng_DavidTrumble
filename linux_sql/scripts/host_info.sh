# Author: david trumble
# File: host_info.sh



#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


echo $psql_host
echo $psql_port
echo $db_name
echo $psql_user
echo $psql_password

echo ' '

echo $1
echo $2
echo $3
echo $4
echo $5

echo ' '


#Check # of args
#if [ $# -ne 5 ] then 
#  exit 1
#fi


#Save machine statistics in MB and current machine hostname to variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

echo $vmstat_mb
echo $hostname

#Retrieve hardware specification variables
#xargs is a trick to trim leading and trailing white spaces
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
echo $memory_free

#cpu_idle=$(echo "$vmstat_mb" | awk '{}' | xargs)
#echo $cpu_idle

#cpu_kernel=$(echo "$vmstat_mb" ...
#echo $cpu_kernel

#disk_io=$(vmstat -d | awk '{print $10}' ...
#echo $disk_io

#disk_available=$(df -BM / ...
#echo disk_available


#Current time in `2019-11-26 14:40:19` UTC format
#timestamp=$(vmstat -t | awk ...
#echo timestamp

#Subquery to find matching id in host_info table
#host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";


#insert server usage data into host_usage table
#insert_stmt="INSERT INTO host_usage(timestamp, ...) VALUES('$timestamp', ..."


#set up env var for pql cmd
#export PGPASSWORD=$psql_password 


#Insert date into a database
#psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

#exit $?
