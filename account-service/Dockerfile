
FROM maven:3.8.6-amazoncorretto-17 AS build-env
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline
RUN mvn spring-javaformat:help

COPY . ./
RUN mvn spring-javaformat:apply
RUN mvn package -DfinalName=demo-discoveryserver

FROM 17-alpine3.15-jdk
#EXPOSE 8080
WORKDIR /app

COPY --from=build-env /app/target/account-service.jar ./account-service.jar
CMD ["/usr/bin/java", "-jar", "/app/account-service.jar"]