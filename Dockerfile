# syntax=docker/dockerfile:experimental

ARG IMAGE_BASE
ARG JAVA_VERSION
ARG RUNTIME_BUILD
ARG RUNTIME_FINAL

FROM ${IMAGE_BASE}:${JAVA_VERSION}-${RUNTIME_BUILD} AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM ${IMAGE_BASE}:${JAVA_VERSION}-${RUNTIME_FINAL} AS final
WORKDIR /app
EXPOSE 8000
COPY --from=build /app/target/*.jar /app/*.jar
ENTRYPOINT [ "java", "-jar", "/app/*.jar" ]


