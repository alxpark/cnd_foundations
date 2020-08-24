# new project
export CIRCUITBREAKER_PROJECT_NAME=circuitbreaker-demo-userXX
oc new-project $CIRCUITBREAKER_PROJECT_NAME

# deploy
mvn clean fabric8:deploy -Popenshift

# testing
export GREETING_SERVICE_URL=http://$(oc get route greeting-service \
-n $CIRCUITBREAKER_PROJECT_NAME -o template --template='{{.spec.host}}')

export NAME_SERVICE_URL=http://$(oc get route name-service \
-n $CIRCUITBREAKER_PROJECT_NAME -o template --template='{{.spec.host}}')

# ok
curl -X GET "${GREETING_SERVICE_URL}/api/greeting"
{"content":"Hello, World!"}

# set status fail
curl -X PUT -H "Content-Type: application/json" \
-d '{"state": "fail"}' "${NAME_SERVICE_URL}/api/state"

# fallback
curl -X GET "${GREETING_SERVICE_URL}/api/greeting"
{"content":"Hello, Fallback!"}

# set status ok
curl -X PUT -H "Content-Type: application/json" \
-d '{"state": "ok"}' "${NAME_SERVICE_URL}/api/state"

# ok
curl -X GET "${GREETING_SERVICE_URL}/api/greeting"
{"content":"Hello, World!"}

