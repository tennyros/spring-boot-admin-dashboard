FROM maven:3.8.1-openjdk-11 AS builder
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/dashboard-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
