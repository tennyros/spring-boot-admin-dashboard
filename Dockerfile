FROM openjdk:11-jdk-slim
COPY target/spring-boot-rest-js-0.0.1-SNAPSHOT.jar spring-boot-rest-js-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-rest-js-0.0.1-SNAPSHOT.jar"]