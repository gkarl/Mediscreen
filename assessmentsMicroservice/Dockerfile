FROM openjdk:11

ARG JAR_FILE=build/libs/assessmentsMicroservice.jar

COPY ${JAR_FILE} assessmentsMicroservice.jar

ENTRYPOINT ["java", "-jar", "/assessmentsMicroservice.jar"]

EXPOSE 8083