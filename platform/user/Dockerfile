FROM maven:3.6.0-jdk-11 AS build
ARG PROFILE=dev
WORKDIR /build
COPY . .
RUN mvn clean package -P$PROFILE

FROM openjdk:11
WORKDIR /work
COPY --from=build build/platform/user/web/target/micro.jar micro.jar
ENTRYPOINT ["java","-jar","micro.jar"]