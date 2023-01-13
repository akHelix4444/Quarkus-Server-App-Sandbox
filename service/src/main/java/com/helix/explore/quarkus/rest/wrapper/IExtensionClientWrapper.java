package com.helix.explore.quarkus.rest.wrapper;

import com.helix.explore.quarkus.dto.ExtensionDto;

import java.util.Set;

public interface IExtensionClientWrapper {

    Set<ExtensionDto> getById(String id);

}
