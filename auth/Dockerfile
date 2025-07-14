#FROM openjdk:21
#LABEL authors="tough"
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} auth.jar
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "/auth.jar"]

FROM openjdk:21-jre-slim
LABEL authors="tough"

COPY build/libs/*.jar auth.jar

# adduser, addgroup 설치
RUN apt-get update && apt-get install -y --no-install-recommends adduser \
    && rm -rf /var/lib/apt/lists/*

RUN addgroup --system builder && adduser --system builder --ingroup builder
USER builder:builder

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/auth.jar"]