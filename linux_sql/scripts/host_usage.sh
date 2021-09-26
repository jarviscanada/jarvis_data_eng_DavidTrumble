# author: david trumble
# file: host_usage.sh 



#Setup and validate arguments (again, don't copy comments)
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


#Check # of args
#if [ $# -ne 5 ] 
#  echo 'incorrect nubmer of arguments'
#  exit 1
# fi


#Save machine statistics in MB and current machine hostname to variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

echo ' '
echo 'vmstat mb'
echo "$vmstat_mb"
echo ' '


#Retrieve hardware specification variables
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | tail -1 | awk '{print $15'} | xargs)


cpu_kernel=$(echo "$vmstat_mb" | tail -1 | awk '{print $14'} | xargs)
disk_io=$(vmstat -d | awk '{print $10}' )
disk_io=$(vmstat -d)
disk_available=$(df -BM)
#disk_available=$(df -BM / )


echo ' ' 
echo 'cpu kernel'
echo "$cpu_kernel"

echo ' '
echo 'disk io'
#echo $disk_io

echo ' '
echo 'disk ioo'
#echo $disk_ioo

echo ' '
echo 'disk available'
#echo $disk_available

#Current time in `2019-11-26 14:40:19` UTC format
#timestamp=$(date '+%Y/%m/%d %H:%M:%S')

echo ' '
echo 'timestamp'
#timestamp=$(vmstat -t)
#echo $timestamp

#timestamp=$(vmstat -t | awk)



exit $?
