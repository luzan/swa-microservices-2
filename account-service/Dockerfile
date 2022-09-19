FROM openjdk:17-alpine
COPY /target/account-service-0.0.1-SNAPSHOT.jar ./account-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","account-service.jar"]