# run
mvn compile vertx:run

# test
curl http://localhost:8080

# build and run uber jar
mvn clean package
ls target/*.jar
java -jar target/vertx-lab1-1.0-SNAPSHOT.jar
