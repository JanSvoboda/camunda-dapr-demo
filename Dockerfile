FROM eclipse-temurin:21-jre
LABEL authors="jansvoboda"

COPY target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]