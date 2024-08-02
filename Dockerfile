FROM amazoncorretto:17-alpine-jdk
LABEL authors="paulmount"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

CMD java \
  -jar app.jar