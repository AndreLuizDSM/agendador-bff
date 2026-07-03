FROM maven:3.9.12-eclipse-temurin-25-alpine AS BUILD
WORKDIR /app
COPY . .

RUN mvn clean install -DskipTest
FROM eclipse-temurin:25-jdk
WORKDIR /app

COPY --from=build /app/target/bff-agendador-de-tarefas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]