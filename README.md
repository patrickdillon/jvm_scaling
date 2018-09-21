# jvm_scaling
## Info
Vertical scaling JVM containers in OpenShift.

This repo holds resources related to research work on scaling JVM containers in OpenShift.

[View the blog post.](https://blog.openshift.com/)

Wildfly server code is in openshift-jee-sample directory.

## Prereqs
- OpenShift
- Apache JMeter, located in this folder
- Directory named 'jmeter-results'

## To run tests
1. Give permissions to write log files. One way of doing this is, as admin, run `oc create -f hostpathscc.yaml`.
2. `oc create -f server-service.yaml`
3. GC logs are being saved in an emptyDir, which will be deleted when the pod shuts down. To save gc logs to your local disk change each deployment to use a hostPath, with a path on your local machine rather than an emptyDir.
4. `./run-tests.sh`


