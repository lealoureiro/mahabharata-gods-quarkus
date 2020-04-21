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

## Prepare and run MockServer to stub necessary services:
```bash
docker build -f mockserver/Dockerfile -t mahabharata-gods-quarkus-mockserver .
docker run -i --rm -p 5000:5000 mahabharata-gods-quarkus-mockserver
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
docker run -i --rm -p8080:8080 mahabharata-gods-quarkus:latest
```