# ---- Stage 1: Build with Gradle ----
FROM gradle:8.5-jdk21 AS builder

# Copy project files
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Build the app (skip tests for speed)
RUN gradle clean build -x test

# ---- Stage 2: Minimal runtime image ----
FROM amazoncorretto:21

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /home/gradle/project/build/libs/srom-0.0.1-SNAPSHOT.jar app.jar

# Cloud Run expects the app to listen on $PORT
ENV PORT=8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]