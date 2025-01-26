FROM openjdk:17
WORKDIR /app
COPY target/SimpleApp-0.0.1-SNAPSHOT.jar SimpleApp-0.0.1-SNAPSHOT.jar

EXPOSE 5050

CMD ["java", "-jar", "SimpleApp-0.0.1-SNAPSHOT.jar"]
