package com.helix.explore.quarkus.interceptor;

import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Provider
public class JaxRequestInterceptor
        implements ContainerRequestFilter {

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerRequest serverRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MDC.put("myTraceKeyId", UUID.randomUUID().toString());

        var method = requestContext.getMethod();
        var path = uriInfo.getPath();
        var address = serverRequest.remoteAddress().toString();

        log.info("Log from JaxRequestInterceptor - Request {} {} from IP {}", method, path, address);
    }

}
