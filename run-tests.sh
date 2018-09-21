#!/bin/sh
for i in 25 30 40 70
do
    echo "begin test $i $j"
    oc delete deploy scaling
    oc create -f deployment-$i.yaml
    sleep 20
    ./apache-jmeter-4.0/bin/jmeter -n -t scale-load.jmx -l ./jmeter-results/`date +%d-%H%M`-$i-results.jtl
    oc delete deploy scaling
    echo "test $i $j completed"
done
