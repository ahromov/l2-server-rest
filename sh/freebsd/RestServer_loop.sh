#!/bin/sh

user=$1
pidfile=$2

#/usr/local/openjdk14/bin/java -Xms128m -Xmx1g -jar l2rest.war --spring.config.location=application.properties &
/usr/sbin/daemon -f -u ${user} -o /usr/home/andrew/l2-server-rest/log/app.log /usr/local/openjdk14/bin/java -Xms128m -Xmx1g -jar l2rest.war --spring.config.location=application.properties &
let pid=$!+1

echo ${pid} > ${pidfile}

