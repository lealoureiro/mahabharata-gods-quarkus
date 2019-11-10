package org.leandroloureiro.mahabharatagods.service;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletionStage;


@RegisterRestClient
public interface IndianGods {

    /**
     * Gets a list of Indian God Name
     *
     * @return the list with Indian Gods names
     */
    @GET
    @Path("/jabrena/latency-problems/indian")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<List<String>> getGodList();

}
