package org.leandroloureiro.mahabharatagods.service;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
@CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.2, delay = 1000)
@Timeout(5000)
@Retry(maxRetries = 1)
public interface IndianGod {


    /**
     * Get the Wikipedia page of god if exists to check if the India God is valid
     *
     * @param name the name of possible Indian God
     * @return the content of Wikipedia page for a Indian God
     */
    @GET
    @Path("/wiki/{name}")
    @Produces(MediaType.TEXT_HTML)
    CompletionStage<String> getIndianGod(@PathParam("name") String name);

}
