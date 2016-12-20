#!/bin/sh
value=`mvn exec:exec -Dkenteken=$1|grep "Gevonden"`
echo "I read [$value]"
