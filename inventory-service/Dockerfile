#----------------
#   Build Stage
#----------------
FROM gradle:8-jdk21-alpine AS builder

# Copy project files to the Gradle container
COPY --chown=gradle:gradle . /home/gradle/src

# Set the working directory
WORKDIR /home/gradle/src

# Build the project
RUN GRADLE_OPTS="-Dorg.gradle.daemon=true" gradle build -x test

#------------
#   Package
#------------
FROM openjdk:21-jdk

# Create a directory for the application
RUN mkdir /app

# Copy the built jar file from the build stage
COPY --from=builder /home/gradle/src/build/libs/inventory-service-0.0.1-SNAPSHOT.jar /app/inventory-service.jar

# Set environment variables
ENV MONGO_DB_USERNAME=admin \
    MONGO_DB_PWD=password

# Expose the application port
EXPOSE 8086

# Optional health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s \
  CMD curl -f http://localhost:8086/actuator/health || exit 1

# Run the jar file as the entry point
ENTRYPOINT ["java", "-jar", "/app/inventory-service.jar"]
