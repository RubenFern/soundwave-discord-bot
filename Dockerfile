# --- Build ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# --- Runtime ---
FROM eclipse-temurin:21-jre
WORKDIR /app
RUN apt-get update && apt-get install -y \
    libopus-dev \
    libsodium-dev \
    fontconfig \
    libfreetype6 \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/SoundWave.jar app.jar

ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-jar", "app.jar"]