FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw clean package -Dmaven.test.skip

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/runner.jar
ENTRYPOINT ["java", "-jar", "runner.jar"]


