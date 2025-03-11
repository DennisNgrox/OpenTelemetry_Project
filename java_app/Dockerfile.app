# Usar a imagem do OpenJDK 8 para build
FROM openjdk:8-jdk as build

WORKDIR /app

# Atualizar as fontes do apt para usar um mirror atualizado
RUN apt-get update && apt-get install -y maven

# Copiar o código fonte
COPY . .

# Compilar o projeto sem usar o Maven Wrapper (mvnw)
RUN mvn clean install -DskipTests

# Usar a mesma imagem para o runtime com Java 8
FROM openjdk:8-jre

WORKDIR /app

COPY --from=build /app/target/user-api-consumer-0.0.1-SNAPSHOT.jar /app/app.jar
ADD --chmod=644 https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar


ENV OTEL_TRACES_EXPORTER=otlp
ENV OTEL_METRICS_EXPORTER=otlp
ENV OTEL_EXPORTER_OTLP_ENDPOINT="http://otel-collector:4318"

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "/app/app.jar"]