FROM openjdk:17-alpine
COPY /target/stock-service-0.0.1-SNAPSHOT.jar ./stock-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","stock-service.jar"]