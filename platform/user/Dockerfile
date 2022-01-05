FROM maven:3.6.0-jdk-11 AS build
ARG PROFILE=dev
WORKDIR /build
COPY ./pom.xml ./pom.xml
COPY ./api ./api
COPY ./web ./web
COPY ./repository ./repository
COPY ./service ./service
COPY ./logger ./logger
RUN mvn clean package -P$PROFILE

FROM openjdk:11
WORKDIR /work
COPY --from=build /build/web/target/micro.jar micro.jar
ENTRYPOINT ["java","-jar","micro.jar"]