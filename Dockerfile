# ===== STAGE 1: Build =====
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

COPY . .
RUN ./gradlew clean bootJar

# ===== STAGE 2: Run =====
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Add non-root user
RUN adduser -D appuser
USER appuser

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=10s --timeout=3s \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
