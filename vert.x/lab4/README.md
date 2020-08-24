# build
mvn clean package

# create project
export HEALTHCHECK_PROJECT_NAME=healthcheck-demo-userXX
oc new-project $HEALTHCHECK_PROJECT_NAME

# build and deploy
mvn clean fabric8:deploy -Popenshift


# check
oc get pods

oc logs -f health-check-vertx-1-lc9z9

# test
export HEALTHCHECK_DEMO_URL=http://$(oc get route health-check-vertx \
-n $HEALTHCHECK_PROJECT_NAME -o template --template='{{.spec.host}}')
curl -X GET "${HEALTHCHECK_DEMO_URL}/api/greeting"
{
  "content" : "Hello, World!"
}

curl -X GET "${HEALTHCHECK_DEMO_URL}/api/killme"
Stopping HTTP server, Bye bye world !

# monitoring pods self-healing
oc get pods -w

