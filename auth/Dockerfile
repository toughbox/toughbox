FROM amazoncorretto:21
LABEL authors="tough"

COPY build/libs/*.jar auth.jar

RUN groupadd -r builder && useradd -r -g builder builder

USER builder:builder

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/auth.jar"]