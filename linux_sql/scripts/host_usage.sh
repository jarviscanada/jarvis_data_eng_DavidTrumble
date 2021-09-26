# author: david trumble
# file: host_usage.sh 



#edit crontab jobs
bash> crontab -e


#crontab every minute to temp log file
* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log


#list crontab jobs
crontab -l


#validate your result from the psql instance
#psql -h localhost -B postgres
#> SELECT * FROM host_usage;
