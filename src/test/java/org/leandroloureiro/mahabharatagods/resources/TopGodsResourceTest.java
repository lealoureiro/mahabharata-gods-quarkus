package org.leandroloureiro.mahabharatagods.resources;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TopGodsResourceTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(8082);
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void testHelloEndpoint() {

        wireMockServer.stubFor(get(urlEqualTo("/jabrena/latency-problems/indian"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("indian.json")
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/wiki/Brahma"))
                .willReturn(aResponse().withHeader("text/html", "application/json")
                        .withStatus(200)
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/wiki/Rama"))
                .willReturn(aResponse().withHeader("text/html", "application/json")
                        .withStatus(200)
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/wiki/Hanuman"))
                .willReturn(aResponse().withHeader("text/html", "application/json")
                        .withStatus(200)
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/wiki/Lakshmi"))
                .willReturn(aResponse().withHeader("text/html", "application/json")
                        .withStatus(200)
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/wiki/Shiva"))
                .willReturn(aResponse().withHeader("text/html", "application/json")
                        .withStatus(200)
                        .withUniformRandomDelay(10, 5000)));

        wireMockServer.stubFor(get(urlEqualTo("/stream/TheMahabharataOfKrishna-dwaipayanaVyasa/MahabharataOfVyasa-EnglishTranslationByKMGanguli_djvu.txt"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("MahabharataOfVyasa-EnglishTranslationByKMGanguli_djvu.txt")
                        .withUniformRandomDelay(10, 5000)));

        given()
                .when().get("/top-gods")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "[0].name", is("Brahma"),
                        "[0].hitCount", is(8100),
                        "[1].name", is("Rama"),
                        "[1].hitCount", is(845),
                        "[2].name", is("Hanuman"),
                        "[2].hitCount", is(54)
                );
    }

}