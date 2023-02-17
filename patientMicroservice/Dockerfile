FROM openjdk:11

ARG JAR_FILE=build/libs/patientMicroservice.jar

COPY ${JAR_FILE} patientMicroservice.jar

ENTRYPOINT ["java", "-jar", "/patientMicroservice.jar"]

EXPOSE 8081