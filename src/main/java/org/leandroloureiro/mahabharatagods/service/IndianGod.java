package org.leandroloureiro.mahabharatagods.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
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
