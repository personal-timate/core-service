FROM gradle:7.5-jdk17 AS builder

LABEL maintainer="Nicholas Dietz @ Timate"
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY src/ src/
RUN gradle --no-daemon build --stacktrace -x test


FROM gcr.io/distroless/java:17

WORKDIR /timate
COPY --from=builder /app/build/libs/*.jar ./app.jar
EXPOSE 8080
CMD [ "-Xmx256m", "-Xss32m", "-Djava.security.egd=file:/dev/./urandom","./app.jar"]