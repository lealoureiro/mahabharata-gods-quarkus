package org.leandroloureiro.mahabharatagods.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class IndianGodLoggingFilter implements ClientRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(IndianGodLoggingFilter.class);

    @Override
    public void filter(final ClientRequestContext context) {
        LOG.info("Checking the existence of Indian God in Wikipedia {}.", context.getUri());
    }
}
