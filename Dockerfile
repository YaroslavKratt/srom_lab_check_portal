# Use Amazon Corretto 21 as base image
FROM gradle:8.5-jdk21 AS builder

# Copy project files
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Build the app
RUN gradle clean build -x test

FROM amazoncorretto:21


# Set working directory
WORKDIR /app

# Copy the built JAR into the image
COPY build/libs/srom-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]