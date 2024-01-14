#FROM openjdk:17-alpine
#
## 어플리케이션 소스들만 따로 관리하는 Working Directory 생성
#WORKDIR /usr/src/app
#
## ARG : 변수 선언
#ARG JAR_PATH=./build/libs
#
## 빌드한 jar 파일을 Docker 컨테이너 내부로 옮겨줌.
#COPY ${JAR_PATH}/CRUD-basic-0.0.1-SNAPSHOT.jar ${JAR_PATH}/CRUD-basic-0.0.1-SNAPSHOT.jar
#
## 문제점 : COPY 의 경우 코드 변경 시 jar 파일 빌드, Docker 이미지 생성, 실행까지 모두 해주어야 함. 비효율적
## 도커 이미지 실행시 -v (Volume 옵션) 사용함으로써 실행에 필요한 파일들을 컨테이너 내부에서 직접 접근.
## -v 사용의 경우 COPY 필요 없지만 Volume 이 지정되지 않거나 오류가 있을 때 COPY 를 이용하기 때문에 활용하는 것이 좋음.
#
#CMD ["java","-jar","./build/libs/CRUD-basic-0.0.1-SNAPSHOT.jar"]
#
#
#FROM openjdk:17-alpine
#
## 어플리케이션 소스들만 따로 관리하는 Working Directory 생성
#WORKDIR /usr/src/app
#
## 빌드한 jar 파일을 Docker 컨테이너 내부로 옮겨줌.
#COPY ./build/libs/CRUD-basic-0.0.1-SNAPSHOT.jar CRUD-basic-0.0.1-SNAPSHOT.jar
#
#CMD ["java","-jar","CRUD-basic-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:17-jdk
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]


FROM openjdk:17-alpine

# 어플리케이션 소스들만 따로 관리하는 Working Directory 생성
WORKDIR /usr/src/app

# 빌드한 jar 파일을 Docker 컨테이너 내부로 옮겨줌.
COPY ./build/libs/CRUD-basic-0.0.1-SNAPSHOT.jar CRUD-basic-0.0.1-SNAPSHOT.jar

#COPY src/main/resources/static /app/static

CMD ["java","-jar","CRUD-basic-0.0.1-SNAPSHOT.jar"]
