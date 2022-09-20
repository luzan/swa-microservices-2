FROM openjdk:17-alpine
COPY /target/eureka-server-0.0.1-SNAPSHOT.jar ./eureka-server.jar
RUN apk --no-cache add curl
ENTRYPOINT ["java", "-jar","eureka-server.jar"]