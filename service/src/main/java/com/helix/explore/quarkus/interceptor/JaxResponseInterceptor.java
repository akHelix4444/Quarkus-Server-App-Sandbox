package com.helix.explore.quarkus.interceptor;

import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Slf4j
@Provider
public class JaxResponseInterceptor
        implements ContainerResponseFilter {

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerResponse serverResponse;

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        var method = requestContext.getMethod();
        var path = uriInfo.getPath();
        var statusCode = serverResponse.getStatusCode();

        log.info("Log from JaxResponseInterceptor - response {} {} with code {}", method, path, statusCode);

        MDC.remove("myTraceKeyId");
    }

}
