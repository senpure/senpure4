#!/bin/bash
if [ ! -n "$1" ] ;then
    name=texas.jar
else
    name=$1
fi
echo name is $name
pid=`ps -ef|grep "$name" | grep -v "$0"| grep -v "grep" | awk '{print $2}'`
echo pid is $pid
ter=`tty`
#echo ter is $ter
endStr=",2) , 1)"
#echo endStr is $endStr
callStr="call dup2(open ("\"$ter\"$endStr
gdb -p $pid >/dev/null << EOF
$callStr
quit
EOF
