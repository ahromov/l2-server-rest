#!/bin/sh

user=$1
pidfile=$2

/usr/bin/gzip log/app.log && mv log/app.log.gz "log/`date +%Y-%m-%d_%H-%M-%S`_app.log.gz"
#/usr/local/openjdk14/bin/java -Xms128m -Xmx1g -jar l2rest.war --spring.config.location=application.properties &
# /usr/sbin/daemon -f -u ${user} -o /usr/home/andrew/l2-server-rest/log/app.log /usr/local/openjdk14/bin/java -Xms128m -Xmx1g -jar l2rest.war --spring.config.location=application.properties &
/usr/sbin/daemon -f -u ${user} -o /usr/home/${user}/l2-server-rest/log/app.log /usr/local/openjdk14/bin/java -Xms128m -Xmx1g -jar l2-server-rest-0.0.1-SNAPSHOT.jar
chmod 700 /usr/home/${user}/l2-server-rest/log/app.log
# let pid=$!+1

# echo ${pid} > ${pidfile}
# cat ${pidfile}


