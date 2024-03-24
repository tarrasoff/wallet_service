FROM openjdk:17-jdk-slim-buster

COPY testExerciseWallet-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8080
ENTRYPOINT java -jar /opt/app.jar