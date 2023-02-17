FROM openjdk:11

ARG JAR_FILE=build/libs/noteMicroservice.jar

COPY ${JAR_FILE} noteMicroservice.jar

ENTRYPOINT ["java", "-jar", "/noteMicroservice.jar"]

EXPOSE 8082