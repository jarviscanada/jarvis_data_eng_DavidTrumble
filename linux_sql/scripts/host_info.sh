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
echo 'number of arguments'
echo $#


#Check # of args
#if [ $# -ne 5 ] then 
#  exit 1
#fi


#lscpu
lscpu_out=$(lscpu)


#hostname
hostname=$(hostname -f)


#cpu number
cpu_number=$(echo "$lscpu_out" | head -4 | tail -1 | awk '{print $2}' | xargs)


#cpu architect
cpu_architecture=$(echo "$lscpu_out" | head -1 | awk '{print $2}' | xargs)


#cpu model
cpu_model=$(echo "$lscpu_out" | egrep "^Model" | tail -1 | awk '{print $3,$4,$5,$6,$7}' | xargs)


#cpu mhz
cpu_mhz=$(echo "$lscpu_out" | tail -10 | head -1 | awk '{print $3}' | xargs)


#cpu mhz no K
cpu_mhz=$(echo "$lscpu_out" | tail -4 | head -1 | awk '{print $3}' | xargs)
lcpu_mhz=$(echo "$lscpu_out" | tail -4 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs)
lld_mhz=$(echo "$lscpu_out" | tail -6 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs) 
lli_mhz=$(echo "$lscpu_out" | tail -5 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs) 
l3_mhz=$(echo "$lscpu_out" | tail -3 | head -1 | awk '{print $3}' | sed 's/.$//' | xargs)


#total mem 
total_mem=$(($l3_mhz + $lli_mhz + $lld_mhz + $lcpu_mhz))


#timestamp y/m/d h/m/s - utc
timestamp=$(date '+%Y/%m/%d %H:%M:%S')






exit $?




