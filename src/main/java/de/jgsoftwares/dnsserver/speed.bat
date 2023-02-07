#! /bin/bash

start=$1
step=${3:-1}

while [ $start -le $2 ]
do
	echo -n '.'
	nslookup -silent -retry=1 -port=5353 www.test.com localhost &
	start=$(($start + $step)) 
done
