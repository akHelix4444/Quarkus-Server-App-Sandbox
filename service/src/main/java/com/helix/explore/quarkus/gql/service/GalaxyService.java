package com.helix.explore.quarkus.gql.service;

import com.helix.explore.quarkus.dto.gql.Character;
import com.helix.explore.quarkus.dto.gql.FilmDto;
import com.helix.explore.quarkus.dto.gql.HeroDto;
import com.helix.explore.quarkus.dto.gql.LightSaber;
import com.helix.explore.quarkus.dto.gql.SearchResult;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class GalaxyService {

    private List<HeroDto> heroes = new ArrayList<>();
    private List<FilmDto> films = new ArrayList<>();

    public GalaxyService() {
        // Films
        var aNewHope = new FilmDto();
        aNewHope.setEpisodeID(4);
        aNewHope.setTitle("A New Hope");
        aNewHope.setDirector("George Lucas");
        aNewHope.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));

        var theEmpireStrikesBack = new FilmDto();
        theEmpireStrikesBack.setEpisodeID(5);
        theEmpireStrikesBack.setTitle("The Empire Strikes Back");
        theEmpireStrikesBack.setDirector("George Lucas");
        theEmpireStrikesBack.setReleaseDate(LocalDate.of(1980, Month.MAY, 21));

        var returnOfTheJedi = new FilmDto();
        returnOfTheJedi.setEpisodeID(6);
        returnOfTheJedi.setTitle("Return Of The Jedi");
        returnOfTheJedi.setDirector("George Lucas");
        returnOfTheJedi.setReleaseDate(LocalDate.of(1983, Month.MAY, 25));

        films.add(aNewHope);
        films.add(theEmpireStrikesBack);
        films.add(returnOfTheJedi);

        // Heroes
        var luke = new HeroDto();
        luke.setName("Luke");
        luke.setSurname("Skywalker");
        luke.setHeight(1.7);
        luke.setMass(73);
        luke.setLightSaber(LightSaber.GREEN);
        luke.setDarkSide(Boolean.FALSE);
        luke.setEpisodeIds(Arrays.asList(4, 5, 6));

        var leia = new HeroDto();
        leia.setName("Leia");
        leia.setSurname("Organa");
        leia.setHeight(1.5);
        leia.setMass(51);
        leia.setDarkSide(Boolean.FALSE);
        leia.setEpisodeIds(Arrays.asList(4, 5, 6));

        var vader = new HeroDto();
        vader.setName("Darth");
        vader.setSurname("Vader");
        vader.setHeight(1.9);
        vader.setMass(89);
        vader.setLightSaber(LightSaber.RED);
        vader.setDarkSide(Boolean.TRUE);
        vader.setEpisodeIds(Arrays.asList(4, 5, 6));

        heroes.add(luke);
        heroes.add(leia);
        heroes.add(vader);
    }

    public List<FilmDto> getAllFilms() {
        return films;
    }

    public FilmDto getFilmById(int id) {
        return films.get(id);
    }

    public List<HeroDto> getHeroesByFilm(FilmDto film) {
        return heroes.stream()
                .filter(hero -> hero.getEpisodeIds().contains(film.getEpisodeID()))
                .toList();
    }

    public List<List<HeroDto>> getHeroesByFilmList(List<FilmDto> films) {
        return films.stream()
                .map(this::getHeroesByFilm)
                .toList();
    }

    public List<Character> getCharactersByFilm(FilmDto film) {
        return getHeroesByFilm(film).stream()
                .map(Character.class::cast)
                .toList();
    }

    public List<SearchResult> getAllSearchResults() {
        var searchResults = new ArrayList<SearchResult>();

        searchResults.addAll(films);
        searchResults.addAll(heroes);

        return searchResults;
    }

    public void addHero(HeroDto hero) {
        heroes.add(hero);
    }

    public HeroDto deleteHeroById(int id) {
        return heroes.remove(id);
    }

    List<HeroDto> getHeroesBySurname(String surname) {
        return heroes.stream()
                .filter(hero -> hero.getSurname().equals(surname))
                .toList();
    }

}
