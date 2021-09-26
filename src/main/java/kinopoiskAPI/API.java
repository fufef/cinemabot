package kinopoiskAPI;

import kinopoiskAPI.HTTPRequest.HTTPRequest;
import kinopoiskAPI.JsonParser.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class API {
    private static final String domain;

    static {
        domain = "https://kinopoiskapiunofficial.tech/api/";
    }

    public static JSONObject getIdOfCountriesAndGenres() {
        String url = String.format("%sv2.1/films/filters", domain);
        return getRequestResult(url);
    }

    public static JSONObject getInformationAboutFilmById(int filmId) {
        String url = String.format("%sv2.2/films/%d/", domain, filmId);
        return getRequestResult(url);
    }

    public static JSONObject getInformationAboutFilmsByFilter(Filter filter) {
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
        String url = String.format("%sv2.1/films/search-by-filters?%s", domain, filtersInRequest);
        return getRequestResult(url);
    }

    private static JSONObject getRequestResult(String url) {
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

    private static JSONObject getJsonObjectFromString(String result) {
        try {
            return JsonParser.Parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}