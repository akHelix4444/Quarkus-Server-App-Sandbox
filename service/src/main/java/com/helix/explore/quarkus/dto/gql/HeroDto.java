package com.helix.explore.quarkus.dto.gql;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@RegisterForReflection
public class HeroDto
        implements Character {

    private String name;
    private String surname;
    private Double height;
    private Integer mass;
    private Boolean darkSide;
    private LightSaber lightSaber;
    @EqualsAndHashCode.Exclude
    private List<Integer> episodeIds = new ArrayList<>();

}
