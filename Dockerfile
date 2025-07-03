# Use an OpenJDK 24 image as a base
#FROM maven:3.9.6-openjdk-21 AS builder
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .

# Download and cache dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application  creating a jar file
RUN mvn package -DskipTests

# Use a lightweight base image for the runtime environment
#FROM openjdk:21-jdk-slim
FROM eclipse-temurin:21-jdk

# Create a non-root user and group
#RUN addgroup -S appgroup
RUN groupadd appgroup && useradd -g appgroup -m -s /bin/bash appuser


# Set the working directory in the container
WORKDIR /app

# Copy the built .war file from the builder stage
COPY --from=builder /app/target/*.jar /app/scorpion.jar

# Change ownership of application files
#RUN chown -R appuser:appgroup /app && \
#    mkdir -p /notifications/files && \
#    chown -R appuser:appgroup /notifications/files

RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "scorpion.jar"]