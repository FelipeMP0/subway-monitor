FROM openjdk:8-alpine
LABEL maintainer=felipemp
WORKDIR /app
COPY libs libs/
COPY resources resources/
COPY classes classes/
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.subwaymonitor.server.Application"]
EXPOSE 8080 8080
