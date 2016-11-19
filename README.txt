Run manual:
    install java (https://java.com/ru/download/)
    install maven (https://maven.apache.org/download.cgi/)
    set properties: /src/main/resources/app.properties
    cd <project_path>/
    mvn clean package -DskipTests
    deploy <project_path>/target/evelopers-skill-test-0.0.1-SNAPSHOT.war to application server

Comment:
   <project_path>/src/main/resources/persons.csv input data file which contains birthday information

