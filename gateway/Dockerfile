FROM amazoncorretto:21
LABEL authors="tough"

COPY build/libs/*.jar gateway.jar

# groupadd, useradd 설치
RUN yum install -y shadow-utils && yum clean all

RUN groupadd -r builder && useradd -r -g builder builder

USER builder:builder

EXPOSE 8080 9010
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "gateway.jar"]