FROM openjdk:11

ARG JAR_FILE=build/libs/frontMediscreen.jar

COPY ${JAR_FILE} frontMediscreen.jar

ENTRYPOINT ["java", "-jar", "/frontMediscreen.jar"]

EXPOSE 8080