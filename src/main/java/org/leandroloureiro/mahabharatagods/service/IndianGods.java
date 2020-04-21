package org.leandroloureiro.mahabharatagods.service;


import org.leandroloureiro.mahabharatagods.logging.IndianGodsLoggingFilter;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletionStage;


@RegisterRestClient
@CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.2, delay = 1000)
@Timeout(5000)
@Retry(maxRetries = 1)
@RegisterProvider(IndianGodsLoggingFilter.class)
public interface IndianGods {

    /**
     * Gets a list of Indian God Name
     *
     * @return the list with Indian Gods names
     */
    @GET
    @Path("/gods.json")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<List<String>> getGodList();

}
