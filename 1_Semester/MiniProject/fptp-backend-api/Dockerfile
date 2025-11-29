# ---- Stage 1: Build the application ----
FROM gradle:8.9-jdk21 AS builder

WORKDIR /app

# Copy Gradle config first for dependency caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon || true

# Copy application source
COPY . .


RUN chmod +x gradlew
# Build the JAR (skip tests for faster CI/CD)
RUN ./gradlew build

# ---- Stage 2: Run the application ----
FROM openjdk:21-ea-21-jdk-slim

WORKDIR /app

# Copy JAR from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Default environment vars (optional, can be overridden by docker-compose)
ENV SPRING_PROFILES_ACTIVE=docker

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
