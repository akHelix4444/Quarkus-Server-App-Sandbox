package com.helix.explore.quarkus.rest.client;

import com.helix.explore.quarkus.dto.rest.ExtensionDto;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Set;

@Path("/extensions")
@RegisterRestClient(configKey = "my-extensions-rest-client")
public interface IExtensionClientService {

    @GET
    Set<ExtensionDto> getById(@QueryParam("id") String id);

    @GET
    Uni<Set<ExtensionDto>> getByIdAsync(@QueryParam("id") String id);

}
