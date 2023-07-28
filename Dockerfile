FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "CRUD-basic-0.0.1-SNAPSHOT.jar"]
