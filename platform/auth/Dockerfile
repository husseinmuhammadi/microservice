FROM maven:3.6.0-jdk-11 AS build
WORKDIR /build
COPY pom.xml pom.xml
COPY api api
COPY web web
COPY service service
RUN mvn clean package

FROM openjdk:11
WORKDIR /work
COPY --from=build /build/web/target/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=stage","auth-service.jar"]