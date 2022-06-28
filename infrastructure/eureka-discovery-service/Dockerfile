FROM maven:3.6.0-jdk-11 AS build
WORKDIR /work
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package

FROM openjdk:11
WORKDIR /work
COPY --from=build /work/target/eureka-discovery-service.jar eureka-discovery-service.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=stage","eureka-discovery-service.jar"]
