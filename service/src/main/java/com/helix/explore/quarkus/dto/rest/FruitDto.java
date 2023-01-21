package com.helix.explore.quarkus.dto.rest;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@RegisterForReflection
public class FruitDto {

    private String name;
    private Integer count;
    private String description;

}
