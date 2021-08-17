FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine

WORKDIR /app

FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Xmx521m" ,"-jar", "app.jar"]