FROM maven:3.6.0-jdk-11 AS build
ARG PROFILE=not-defined
WORKDIR /work
COPY ./pom.xml ./pom.xml
COPY ./infrastructure ./infrastructure
COPY ./platform ./platform
RUN mvn clean package -P$PROFILE

FROM openjdk:11
WORKDIR /work
COPY --from=build /work/infrastructure/zuul-api-gateway/target/zuul-api-gateway.jar zuul-api-gateway.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","zuul-api-gateway.jar"]