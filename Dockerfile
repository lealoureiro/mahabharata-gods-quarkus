## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/centos-quarkus-maven:19.2.1 AS build

## Enable SSL libraries for HTTPS calls
RUN mkdir -p /tmp/ssl-libs/lib \
  && cp /opt/graalvm/jre/lib/security/cacerts /tmp/ssl-libs \
  && cp /opt/graalvm/jre/lib/amd64/libsunec.so /tmp/ssl-libs/lib/

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
USER root
RUN chown -R quarkus /usr/src/app
USER quarkus
RUN mvn -f /usr/src/app/pom.xml -Pnative clean package

## Stage 2 : create the docker final image
FROM debian:stable-slim AS build-env

FROM gcr.io/distroless/base
WORKDIR /work/
COPY --from=build-env /lib/x86_64-linux-gnu/libz.so.1 /lib/x86_64-linux-gnu/libz.so.1
COPY --from=build /usr/src/app/target/*-runner /work/application
COPY --from=build /tmp/ssl-libs/ /work/
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Djava.library.path=/work/lib", "-Djavax.net.ssl.trustStore=/work/cacerts"]