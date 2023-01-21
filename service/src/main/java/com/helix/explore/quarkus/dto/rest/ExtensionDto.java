package com.helix.explore.quarkus.dto.rest;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@RegisterForReflection
public class ExtensionDto {

    private String id;
    private String name;
    private String shortName;
    @EqualsAndHashCode.Exclude
    private List<String> keywords;

}
