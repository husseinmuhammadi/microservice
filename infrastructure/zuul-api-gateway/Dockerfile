FROM maven:3.6.0-jdk-11 AS build
ARG PROFILE=dev
WORKDIR /work
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package -P$PROFILE

FROM openjdk:11
WORKDIR /work
COPY --from=build /work/target/zuul-api-gateway.jar zuul-api-gateway.jar
EXPOSE 8011
ENTRYPOINT ["java","-jar","zuul-api-gateway.jar"]