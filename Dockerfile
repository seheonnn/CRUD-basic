FROM openjdk:17-alpine

WORKDIR /usr/src/app

ENTRYPOINT ["java", "-jar", "CRUD-basic-0.0.1-SNAPSHOT.jar"]
