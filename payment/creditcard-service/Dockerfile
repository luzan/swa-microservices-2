FROM openjdk:17-alpine
COPY /target/creditcard-service-0.0.1-SNAPSHOT.jar ./creditcard-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","creditcard-service.jar"]