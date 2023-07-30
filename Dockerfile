FROM openjdk:17-alpine
WORKDIR /usr/src/app

# ARG JAR_PATH=./build/libs
# COPY ${JAR_PATH}/CRUD-basic-0.0.1-SNAPSHOT.jar ${JAR_PATH}/CRUD-basic-0.0.1-SNAPSHOT.jar

# 현재 디렉토리에서 바로 JAR 파일 복사
COPY ./CRUD-basic-0.0.1-SNAPSHOT.jar CRUD-basic-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","CRUD-basic-0.0.1-SNAPSHOT.jar"]
