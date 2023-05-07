FROM maven:3.8.5-openjdk-17-slim as maven

WORKDIR /app
COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests=true -q

FROM openjdk:11.0.9-jre-slim as jre

COPY --from=maven /app/target/restoran.jar .
CMD java -Xms128m -Xmx2048m -jar restoran.jar

EXPOSE 8080
