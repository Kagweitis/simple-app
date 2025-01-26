FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./
COPY src ./src

# Run Maven to build the JAR
RUN mvn clean package -DskipTests

# Use a smaller image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/SimpleApp-0.0.1-SNAPSHOT.jar SimpleApp-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 5050

# Command to run the application
CMD ["java", "-jar", "app.jar"]
