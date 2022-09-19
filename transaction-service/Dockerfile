FROM openjdk:17-alpine
COPY /target/transaction-service-0.0.1-SNAPSHOT.jar ./transaction-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","transaction-service.jar"]