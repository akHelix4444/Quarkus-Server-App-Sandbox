package com.helix.explore.quarkus.interceptor;

import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Slf4j
@Provider
public class JaxClientRequestInterceptor
        implements ClientRequestFilter {

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerRequest serverRequest;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        var method = requestContext.getMethod();
        var path = uriInfo.getPath();
        var address = serverRequest.remoteAddress().toString();

        log.info("Log from JaxClientRequestInterceptor - Request {} {} from IP {}", method, path, address);
    }

}
