package kinopoiskAPI;

import kinopoiskAPI.HTTPRequest.HTTPRequest;

import java.io.IOException;

public class API {
    private static final String domain;

    static {
        domain = "https://kinopoiskapiunofficial.tech/api/";
    }

    public static String getIdOfCountriesAndGenres() throws IOException {
        return HTTPRequest.getRequestResults(String.format("%sv2.1/films/filters", domain));
    }

    public static String getInformationAboutFilmById(int filmId) throws IOException {
        return HTTPRequest.getRequestResults(String.format("%sv2.2/films/%d/", domain, filmId));
    }

    public static String getInformationAboutFilmsByFilter(Filter filter) throws IOException {
        StringBuilder filtersInRequest = new StringBuilder("?");
        for (var country :
                filter.countries())
            filtersInRequest.append(String.format("country=%d&", country));
        for (var genre :
                filter.genres())
            filtersInRequest.append(String.format("genre=%d&", genre));
        filtersInRequest.append(String.format(
                "order=%s&type=%s&ratingFrom=%d&ratingTo=%d&yearFrom=%d&yearTo=%d&page=%d",
                filter.order(),
                filter.type(),
                filter.ratingFrom(),
                filter.ratingTo(),
                filter.yearFrom(),
                filter.yearTo(),
                filter.page()));
        return HTTPRequest.getRequestResults(String.format("%sv2.1/films/search-by-filters?%s", domain, filtersInRequest));
    }
}
