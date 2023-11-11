FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

COPY target/ximo-producer-0.0.1-SNAPSHOT.jar /app/ximo-producer-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "ximo-producer-0.0.1-SNAPSHOT.jar"]
