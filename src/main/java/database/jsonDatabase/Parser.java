package database.jsonDatabase;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import database.UserParameters;
import kinopoiskAPI.Filter;

public class Parser {
    public static JsonObject parseUserParametersToJsonObject(UserParameters parameters) {
        JsonObject parametersAsJson = new JsonObject();
        parametersAsJson.put("searchResult", parameters.getSearchResult());
        parametersAsJson.put("filter", parseFilterToJsonObject(parameters.getFilter()));
        parametersAsJson.put("numberOfCurrentFilm", parameters.getNumberOfCurrentFilm());
        parametersAsJson.put("numberOfCurrentPage", parameters.getNumberOfCurrentPage());
        return parametersAsJson;
    }

    public static JsonObject parseFilterToJsonObject(Filter filter) {
        //TODO Рефлексия?
        JsonObject filterAsJson = new JsonObject();
        filterAsJson.put("countries", filter.getCountries());
        filterAsJson.put("genres", filter.getGenres());
        filterAsJson.put("order", filter.getOrder());
        filterAsJson.put("type", filter.getType());
        filterAsJson.put("ratingFrom", filter.getRatingFrom());
        filterAsJson.put("ratingTo", filter.getRatingTo());
        filterAsJson.put("yearFrom", filter.getYearFrom());
        filterAsJson.put("yearTo", filter.getYearTo());
        filterAsJson.put("page", filter.getPage());
        return filterAsJson;
    }

    public static UserParameters parseJsonObjectToUserParameters(JsonObject jsonObject) {
        try {
            return new UserParameters(
                    (JsonObject) jsonObject.get("searchResult"),
                    parseJsonObjectToFilter((JsonObject) jsonObject.get("filter")),
                    parseToInt(jsonObject.get("numberOfCurrentFilm")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Filter parseJsonObjectToFilter(JsonObject jsonObject) {
        Filter filter = new Filter();
        filter.setCountries(
                parseToIntArray(jsonObject.get("countries")));
        filter.setGenres(
                parseToIntArray(jsonObject.get("genres")));
        filter.setOrder((String) jsonObject.get("order"));
        filter.setType((String) jsonObject.get("type"));
        filter.setRatingFrom(
                parseToInt(jsonObject.get("ratingFrom")));
        filter.setRatingTo(
                parseToInt(jsonObject.get("ratingTo")));
        filter.setYearFrom(
                parseToInt(jsonObject.get("yearFrom")));
        filter.setYearTo(
                parseToInt(jsonObject.get("yearTo")));
        filter.setPage(
                parseToInt(jsonObject.get("page")));
        return filter;
    }

    private static int[] parseToIntArray(Object array) {
        int[] result;
        try {
            result = (int[]) array;
        } catch (ClassCastException exception) {
            JsonArray jsonArray = (JsonArray) array;
            result = new int[jsonArray.size()];
            for (int i = 0; i < result.length; i++)
                result[i] = parseToInt(jsonArray.get(i));
        }
        return result;
    }

    private static int parseToInt(Object number) {
        return ((Number) number).intValue();
    }
}
