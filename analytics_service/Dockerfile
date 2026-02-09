FROM gradle:9.3.1-jdk-25-and-25 AS builder

WORKDIR /app

COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY gradlew .
COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:25-jdk-alpine AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]
