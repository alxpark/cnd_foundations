# oc new project
export CONFIGMAP_PROJECT_NAME=configmap-demo-userXX
oc new-project $CONFIGMAP_PROJECT_NAME
oc policy add-role-to-user view -n $CONFIGMAP_PROJECT_NAME -z default

# configMap
oc create configmap app-config --from-file=app-config.yml
oc get configmap app-config -o yaml

# deploy
mvn clean fabric8:deploy -Popenshift

# oc get pods
oc get pods
NAME                          READY     STATUS      RESTARTS   AGE
configmap-vertx-1-6mshq       1/1       Running     0          25s
oc logs -f configmap-vertx-1-6mshq


# test
export CONFIGMAP_DEMO_URL=http://$(oc get route configmap-vertx \
-n $CONFIGMAP_PROJECT_NAME -o template --template='{{.spec.host}}')
curl "${CONFIGMAP_DEMO_URL}/api/greeting"
{
  "content" : "Hello, World from a ConfigMap !"
}

# update configmap
oc edit configmap app-config
"Bonjour, %s from a ConfigMap !"
curl "${CONFIGMAP_DEMO_URL}/api/greeting"
{
  "content" : "Bonjour, World from a ConfigMap !"
}

