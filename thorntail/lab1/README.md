# build
mvn clean package
ls target/*.jar

# run
java -jar target/thorntail-inventory-lab1-1.0.0-SNAPSHOT-thorntail.jar
or
mvn thorntail:run

# test
curl http://localhost:8080/api/inventory/329299
