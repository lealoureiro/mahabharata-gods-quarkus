package org.leandroloureiro.mahabharatagods.service;

import com.leandroloureiro.mahabharatagods.logging.MahabharataBookLoggingFilter;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient
@CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.2, delay = 1000)
@Timeout(5000)
@Retry(maxRetries = 1)
@RegisterProvider(MahabharataBookLoggingFilter.class)
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
