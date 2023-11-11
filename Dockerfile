FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target/ximo-producer-0.0.1-SNAPSHOT.jar /app/ximo-producer-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "ximo-producer-0.0.1-SNAPSHOT.jar"]
