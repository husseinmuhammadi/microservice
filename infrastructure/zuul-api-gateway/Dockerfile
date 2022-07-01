FROM maven:3.6.0-jdk-11 AS build
WORKDIR /work
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package

FROM openjdk:11
WORKDIR /work
COPY --from=build /work/target/zuul-api-gateway.jar zuul-api-gateway.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=stage","zuul-api-gateway.jar"]