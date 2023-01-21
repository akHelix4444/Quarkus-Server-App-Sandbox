package com.helix.explore.quarkus.rest.wrapper.impl;

import com.helix.explore.quarkus.dto.rest.ExtensionDto;
import com.helix.explore.quarkus.rest.client.IExtensionClientService;
import com.helix.explore.quarkus.rest.wrapper.IExtensionClientWrapper;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@Slf4j
@ApplicationScoped
public class ExtensionClientWrapper
        implements IExtensionClientWrapper {

    @RestClient
    IExtensionClientService extensionClientService;

    @Override
    public Set<ExtensionDto> getById(String id) {
        log.info("Log from ExtensionClientWrapper - request with id {}", id);
        return extensionClientService.getById(id);
    }

    @Override
    public Uni<Set<ExtensionDto>> getByIdAsync(String id) {
        log.info("Log from ExtensionClientWrapper - async request with id {}", id);
        return extensionClientService.getByIdAsync(id)
                .onItem().invoke(item -> log.info("Result from Uni: {}", item))
                .onFailure().retry().atMost(10);
    }

}
