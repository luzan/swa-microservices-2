FROM openjdk:17-alpine
COPY /target/paypal-service-0.0.1-SNAPSHOT.jar ./paypal-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","paypal-service.jar"]