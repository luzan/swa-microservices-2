FROM openjdk:17-alpine
COPY /target/bankAccount-service-0.0.1-SNAPSHOT.jar ./bankaccount-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","bankaccount-service.jar"]