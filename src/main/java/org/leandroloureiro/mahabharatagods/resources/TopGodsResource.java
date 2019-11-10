package org.leandroloureiro.mahabharatagods.resources;


import org.leandroloureiro.mahabharatagods.logic.TopMahabharataGods;
import org.leandroloureiro.mahabharatagods.model.God;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class TopGodsResource {

    @Inject
    TopMahabharataGods topMahabharataGods;

    @GET
    @Path("/top-gods")
    @Produces(MediaType.APPLICATION_JSON)
    public List<God> getMahabharaGods() {
        return topMahabharataGods.getTopMahabharataGods();
    }

}
