FROM maven:3.6.0-jdk-11 AS build
ARG PROFILE=not-defined
WORKDIR /work
COPY ./pom.xml ./pom.xml
COPY ./infrastructure ./infrastructure
RUN mvn clean package -P$PROFILE

FROM openjdk:11
WORKDIR /work
COPY --from=build /work/infrastructure/eureka-discovery-service/target/eureka-discovery-service.jar eureka-discovery-service.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","eureka-discovery-service.jar"]
