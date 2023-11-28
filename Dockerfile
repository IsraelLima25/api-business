FROM openjdk:17
WORKDIR /app
COPY /target/api-business-1.0.0.jar /app/
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=default", "/app/api-business-1.0.0.jar" ]