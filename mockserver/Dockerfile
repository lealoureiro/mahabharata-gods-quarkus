###
#
# Docker image to run Mock Server with required services
#
# Build the image with following command
#
# docker build -f mockserver/Dockerfile -t mahabharata-gods-quarkus-mockserver .
#
# Then run the container using:
#
# docker run -i --rm -p 8081:8081 mahabharata-gods-quarkus-mockserver
#
###
FROM fabric8/java-alpine-openjdk11-jre:1.6.5

# Be prepared for running in OpenShift too
RUN adduser -G root --no-create-home --disabled-password 1001 \
  && chown -R 1001 /deployments \
  && chmod -R "g+rwX" /deployments \
  && chown -R 1001:root /deployments

COPY src/test/resources/__files /deployments/__files
COPY mockserver/mappings /deployments/mappings
COPY mockserver/wiremock-standalone-2.26.3.jar /deployments/
EXPOSE 8081

# run with user 1001
USER 1001

ENTRYPOINT [ "/deployments/run-java.sh", "--port", "8081" ]


