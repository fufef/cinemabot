package kinopoiskAPI;

import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.httpRequest.HTTPRequest;
import kinopoiskAPI.jsonParser.JsonParser;

import java.io.IOException;
import java.text.ParseException;

public class API {
    private static final String domain;

    static {
        domain = "https://kinopoiskapiunofficial.tech/api/";
    }

    public static JsonObject getIdOfCountriesAndGenres() {
        String url = String.format("%sv2.1/films/filters", domain);
        return getRequestResult(url);
    }

    public static JsonObject getInformationAboutFilmById(long filmId) {
        String url = String.format("%sv2.2/films/%d/", domain, filmId);
        return getRequestResult(url);
    }

    public static JsonObject getInformationAboutFilmsByFilter(Filter filter) {
        StringBuilder filtersInRequest = new StringBuilder("?");
        for (var country :
                filter.getCountries())
            filtersInRequest.append(String.format("country=%d&", country));
        for (var genre :
                filter.getGenres())
            filtersInRequest.append(String.format("genre=%d&", genre));
        filtersInRequest.append(String.format(
                "order=%s&type=%s&ratingFrom=%d&ratingTo=%d&yearFrom=%d&yearTo=%d&page=%d",
                filter.getOrder(),
                filter.getType(),
                filter.getRatingFrom(),
                filter.getRatingTo(),
                filter.getYearFrom(),
                filter.getYearTo(),
                filter.getPage()));
        String url = String.format("%sv2.1/films/search-by-filters?%s", domain, filtersInRequest);
        return getRequestResult(url);
    }

    private static JsonObject getRequestResult(String url) {
        String result;
        try {
            result = HTTPRequest.request(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (result == null) return null;
        return getJsonObjectFromString(result);
    }

    private static JsonObject getJsonObjectFromString(String result) {
        try {
            return JsonParser.Parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
