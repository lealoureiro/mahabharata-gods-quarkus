package com.leandroloureiro.mahabharatagods.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class MahabharataBookLoggingFilter implements ClientRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(MahabharataBookLoggingFilter.class);

    @Override
    public void filter(final ClientRequestContext context) {
        LOG.info("Fetching Mahabharata book content from: {}.", context.getUri());
    }
}
