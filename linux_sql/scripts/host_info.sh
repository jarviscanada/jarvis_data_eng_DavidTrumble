# Author: david trumble
# File: host_info.sh



#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5



#Check # of args
if [ $# -ne 5 ] 
	then 
 	echo 'incorrect number of arguments'
	 exit 1
fi



#lscpu
lscpu_out=$(lscpu)

#hostname
hostname=$(hostname -f)

#cpu number
cpu_number=$(echo "$lscpu_out" | head -4 | tail -1 | awk '{print $2}' | xargs)

#cpu architect
cpu_architecture=$(echo "$lscpu_out" | head -1 | awk '{print $2}' | xargs)

#cpu model
cpu_model=$(echo "$lscpu_out" | egrep "^Model" |  tail -1 | awk '{$1=$2=""; print $0}' | xargs)

#cpu mhz
cpu_mhz=$(echo "$lscpu_out" | tail -10 | head -1 | awk '{print $3}' | xargs) 

#l2_cache
L2_cache=$(echo "$lscpu_out" | tail -4 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs)

#cpu mhz no K
#lld_mhz=$(echo "$lscpu_out" | tail -6 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs) 
#lli_mhz=$(echo "$lscpu_out" | tail -5 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs) 
#l3_mhz=$(echo "$lscpu_out" | tail -3 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs)

#total mem 
#total_mem=$(($l3_mhz + $lli_mhz + $lld_mhz + $L2_cache))
total_mem=$(echo "$lscpu_out" | tail -3 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs)


#timestamp y/m/d h/m/s - utc
timestamp=$(date '+%Y/%m/%d %H:%M:%S')


#insert statement
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) VALUES('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$L2_cache', '$total_mem', '$timestamp')";


#set up env var for pql cmd
export PGPASSWORD=$psql_password


#insert data into database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"


exit $?

