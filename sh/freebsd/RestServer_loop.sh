#!/bin/sh

user=$1
pidfile=$2

/usr/sbin/daemon -u ${user} java -Xms128m -Xmx256m -jar l2rest.war --spring.config.location=application.properties &
let pid=$!+1

echo ${pid} > ${pidfile}

