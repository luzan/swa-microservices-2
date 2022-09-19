FROM openjdk:17-alpine
COPY /target/shipping-service-0.0.1-SNAPSHOT.jar ./shipping-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","shipping-service.jar"]