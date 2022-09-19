FROM openjdk:17-alpine
COPY /target/product-service-0.0.1-SNAPSHOT.jar ./product-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","product-service.jar"]