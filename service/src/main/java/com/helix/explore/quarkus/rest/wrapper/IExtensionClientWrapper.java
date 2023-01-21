package com.helix.explore.quarkus.rest.wrapper;

import com.helix.explore.quarkus.dto.rest.ExtensionDto;
import io.smallrye.mutiny.Uni;

import java.util.Set;

public interface IExtensionClientWrapper {

    Set<ExtensionDto> getById(String id);

    Uni<Set<ExtensionDto>> getByIdAsync(String id);

}
