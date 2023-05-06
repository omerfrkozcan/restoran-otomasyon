FROM openjdk:11.0.9-jre-slim

COPY target/restoran.jar .

CMD java -Xms128m -Xmx2048m -jar restoran.jar
EXPOSE 8080