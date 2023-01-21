package com.helix.explore.quarkus.rest;

import com.helix.explore.quarkus.dto.rest.ExtensionDto;
import com.helix.explore.quarkus.rest.wrapper.IExtensionClientWrapper;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Set;

import static io.quarkiverse.loggingjson.providers.KeyValueStructuredArgument.kv;

@Slf4j
@Path("/extension")
public class ExtensionRestApi {

    @Inject
    IExtensionClientWrapper extensionClientWrapper;

    @GET
    @Path("/id/{id}")
    public Set<ExtensionDto> id(@PathParam("id") String id) {
        log.info("Log from ExtensionRestApi.");
        return extensionClientWrapper.getById(id);
    }

    @GET
    @Path("/id-async/{id}")
    public Uni<Set<ExtensionDto>> idAsync(@PathParam("id") String id) {
        log.info("Log from ExtensionRestApi Async.", kv("test_structured_field", 100));
        return extensionClientWrapper.getByIdAsync(id);
    }

}
