# Mahabharata Gods

REST API to Calculate the popularity of Indian gods in Mahabharata book. 
The goal of the project/challenge is to explore **Quarkus** Libraries.


## Pre-requisites

- Java 11
- httpie (optional for testing)

## Build

```bash
./mvnw clean install
```

## Run

```bash
./mvnw compile quarkus:dev
```

## Test 
```bash
http :8080/top-gods
```

## Native Image with Docker 
```bash
docker build -f src/main/docker/Dockerfile.multistage -t mahabharata-gods-quarkus .
docker run -p8080:8080 mahabharata-gods-quarkus:latest
```