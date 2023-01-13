package com.helix.explore.quarkus.interceptor;

import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Slf4j
@Provider
public class JaxClientResponseInterceptor
        implements ClientResponseFilter {

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerResponse serverResponse;

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) throws IOException {
        var method = requestContext.getMethod();
        var path = uriInfo.getPath();
        var statusCode = serverResponse.getStatusCode();

        log.info("Log from JaxClientResponseInterceptor - response {} {} with code {}", method, path, statusCode);
    }

}
