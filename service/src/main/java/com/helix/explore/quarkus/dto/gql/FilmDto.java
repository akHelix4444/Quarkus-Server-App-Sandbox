package com.helix.explore.quarkus.dto.gql;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@RegisterForReflection
public class FilmDto
        implements SearchResult {

    private String title;
    private Integer episodeID;
    private String director;
    private LocalDate releaseDate;

}
