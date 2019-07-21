#!/bin/sh

pidfile=$1
user=$2

[ -f log/java0.log.0 ] && mv log/java0.log.0 "log/`date +%Y-%m-%d_%H-%M-%S`_java.log"
[ -f log/stdout.log ] && mv log/stdout.log "log/`date +%Y-%m-%d_%H-%M-%S`_stdout.log"
/usr/sbin/daemon -u ${user} /usr/local/openjdk8/bin/java -Xms128m -Xmx256m -jar l2-server-webapp.jar > log/stdout.log &
let pid=$!+1


echo ${pid} > ${pidfile}

