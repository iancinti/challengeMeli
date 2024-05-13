FROM gradle:latest as builder

WORKDIR /app

COPY --chown=gradle:gradle . /app

RUN gradle clean bootJar

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/build/libs/app.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
