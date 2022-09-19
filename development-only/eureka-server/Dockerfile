FROM openjdk:17-alpine
COPY /target/eureka-server-0.0.1-SNAPSHOT.jar ./eureka-server.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","eureka-server.jar"]