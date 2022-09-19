FROM openjdk:17-alpine
COPY /target/order-service-0.0.1-SNAPSHOT.jar ./order-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","order-service.jar"]