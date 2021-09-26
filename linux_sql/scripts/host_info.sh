# Author: david trumble
# File: host_info.sh



#Setup and validate arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5



#of arguments
echo ' '
echo ' '
echo 'number of arguments'
echo $#
echo ' '


#Check # of args
#if [ $# -ne 5 ] then 
#  exit 1
#fi


#lscpu
lspu_out=$(lscpu)


#hostname
echo "hostname"
hostname=$(hostname -f)
echo $hostname
echo ' '


#cpu number
echo "cpu_number"
cpu_number=$(echo "$lspu_out" | head -4 | tail -1 | awk '{print $2}')
echo "$cpu_number"
echo ' '


#cpu architect
echo "cpu_architect"
cpu_architecture=$(echo "$lspu_out" | head -1 | awk '{print $2}')
echo "$cpu_architecture"
echo ' '

echo "cpu_model"
cpu_model=$(echo "$lscpu_out"  | egrep "^Model" | awk '{print $2}')
echo "$cpu_model"
echo ' '

echo "cpu_mhz"
echo ' '

echo "l2_cache"
echo ' '

echo "total_mem"
echo ' '

echo "timestamp"
echo ' '




#exit $?
