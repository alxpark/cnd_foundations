# oc login
oc login ${YOUR-OPENSHIFT-SERVER}
export HELLO_PROJECT_NAME=helloworld-http-userXX
oc new-project $HELLO_PROJECT_NAME

# deploy
mvn clean fabric8:deploy -Popenshift

oc get pods
oc logs -f hello-microservice-1-m73d5

# test
export HELLO_URL=http://$(oc get route hello-microservice \
-n $HELLO_PROJECT_NAME -o template --template='{{.spec.host}}')
curl -X GET "${HELLO_URL}/John"
{"message":"Hello John","served-by": "hello-microservice-1-9r8uv"}

# pod scale up
oc scale --replicas=3 dc hello-microservice
oc get pods
NAME                             READY     STATUS      RESTARTS   AGE
hello-microservice-1-bhc8x       1/1       Running     0          37s
hello-microservice-1-nc6hw       1/1       Running     0          37s
hello-microservice-1-t6szp       1/1       Running     0          3m
hello-microservice-s2i-1-build   0/1       Completed   0          4m
curl -X GET "${HELLO_URL}/John"
{"message":"Hello John","served-by":"hello-microservice-1-t6szp"}
curl -X GET "${HELLO_URL}/John"
{"message":"Hello John","served-by":"hello-microservice-1-bhc8x"}
curl -X GET "${HELLO_URL}/John"
{"message":"Hello John","served-by":"hello-microservice-1-nc6hw"}
curl -X GET "${HELLO_URL}/John"
{"message":"Hello John","served-by":"hello-microservice-1-t6szp"}

# pod scale down
oc scale --replicas=1 dc hello-microservice
