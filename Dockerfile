#Use a base image with Java
FROM maven:3.9.8-eclipse-temurin-21 AS build
#Set the working directory
WORKDIR /app
#Copy the packaged JAR file into the container
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build app/target/NexignTestApplication-0.0.1-SNAPSHOT.jar app.jar

#Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]