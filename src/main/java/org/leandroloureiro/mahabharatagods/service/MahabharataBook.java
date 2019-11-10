package org.leandroloureiro.mahabharatagods.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
public interface MahabharataBook {

    /**
     * Get Mahabharata Of Vyasa book content from web asynchronously
     *
     * @return the content of the book
     */
    @GET
    @Path("/stream/TheMahabharataOfKrishna-dwaipayanaVyasa/MahabharataOfVyasa-EnglishTranslationByKMGanguli_djvu.txt")
    @Produces(MediaType.TEXT_PLAIN)
    CompletionStage<String> getBookContent();

}
