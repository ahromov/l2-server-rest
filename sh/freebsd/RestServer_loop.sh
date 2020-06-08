#!/bin/sh

pidfile=$1
user=$2

/usr/sbin/daemon -u ${user} /usr/local/openjdk8/bin/java -Xms128m -Xmx256m -jar l2-server-rest-0.0.1-SNAPSHOT.war --spring.config.location=application.properties &
let pid=$!+1

echo ${pid} > ${pidfile}

