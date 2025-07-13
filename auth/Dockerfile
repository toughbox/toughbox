FROM openjdk:21
LABEL authors="tough"
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} auth.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/auth.jar"]