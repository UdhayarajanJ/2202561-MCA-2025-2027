# ---- Stage 1: Build the application ----
FROM gradle:8.9-jdk21 AS builder

WORKDIR /app

# Copy only Gradle config first (for caching dependencies)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon || return 0

# Copy the rest of the source code
COPY . .

# Build the JAR file
RUN ./gradlew clean build -x test --no-daemon

# ---- Stage 2: Run the application ----
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port if needed
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
