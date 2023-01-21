package com.helix.explore.quarkus.gql;

import com.helix.explore.quarkus.dto.gql.Character;
import com.helix.explore.quarkus.dto.gql.FilmDto;
import com.helix.explore.quarkus.dto.gql.HeroDto;
import com.helix.explore.quarkus.dto.gql.SearchResult;
import com.helix.explore.quarkus.gql.service.GalaxyService;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@GraphQLApi
public class FilmGraphQLApi {

    BroadcastProcessor<HeroDto> heroBroadcaster = BroadcastProcessor.create();

    @Inject
    GalaxyService galaxyService;

    public FilmGraphQLApi() {
        log.info("Log from FilmGraphQLApi - FilmGraphQLApi default constructor.");
    }

    @Description("Get all Films from a galaxy far far away")
    @Query("allFilms")
    public List<FilmDto> getAllFilms() {
        log.info("Log from FilmGraphQLApi - getAllFilms.");
        return galaxyService.getAllFilms();
    }

    @Description("Get a Films from a galaxy far far away by ID")
    @Query
    public FilmDto getFilmById(@Name("filmId") int id) {
        log.info("Log from FilmGraphQLApi - getFilmById");
        return galaxyService.getFilmById(id);
    }

    // Union example
    @Query
    public List<SearchResult> getSearchResults() {
        log.info("Get all SearchResult implementations.");
        return galaxyService.getAllSearchResults();
    }

    @Mutation
    public HeroDto addHero(HeroDto hero) {
        galaxyService.addHero(hero);
        return hero;
    }

    @Mutation
    public HeroDto broadcastHero(HeroDto hero) {
        galaxyService.addHero(hero);
        heroBroadcaster.onNext(hero);
        return hero;
    }

    @Mutation
    public HeroDto deleteHero(@DefaultValue("0") int id) {
        return galaxyService.deleteHeroById(id);
    }

    @Subscription
    public Multi<HeroDto> heroCreatedEvent() {
        log.info("Log from heroCreatedEvent subscription.");
        return heroBroadcaster;
    }

    // Expand example
    public List<List<HeroDto>> heroes(@Source List<FilmDto> films) {
        log.info("Add heroes list source to film batch.");
        return galaxyService.getHeroesByFilmList(films);
    }

    // Interface example
    public List<Character> characters(@Source FilmDto film) {
        log.info("Add characters list source to film - {}", film.getTitle());
        return galaxyService.getCharactersByFilm(film);
    }

}
