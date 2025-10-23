# Stage 1: build with Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /workspace
# Copy only the files needed for dependency resolution first (cache)
COPY pom.xml .
# If you have a multi-module build, copy parent pom and modules accordingly.
RUN mvn -q -B dependency:go-offline

# Copy full source and build
COPY src ./src
RUN mvn -q -B clean package -DskipTests

# Stage 2: runtime image (smaller)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Optional: create non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy jar produced by builder
COPY --from=builder /workspace/target/titreminexcel-0.0.1-SNAPSHOT.jar app.jar

# Expose the actual server port
EXPOSE 9091

# Set a healthcheck (optional)
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s \
  CMD wget --quiet --tries=1 --spider http://localhost:9091/actuator/health || exit 1

# Start
ENTRYPOINT ["java","-jar","/app/app.jar"]
