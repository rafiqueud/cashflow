# Stage 1: Construir o aplicativo Spring Boot com o Gradle Wrapper
FROM gradle:latest AS builder
WORKDIR /app
COPY . .
RUN ./gradlew bootJar -x test

# Stage 2: Criar uma imagem do Docker mínima para executar o aplicativo
FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]