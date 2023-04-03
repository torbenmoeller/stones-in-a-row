FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /app/build/libs/stones-in-a-row-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]