package database.jsonDatabase;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import database.Database;
import database.UserParameters;
import kinopoiskAPI.Filter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONDatabase implements Database {
    private final File database;
    private final JsonObject databaseData;

    public JSONDatabase() {
        this.database = new File("src/main/java/database/jsonDatabase/database.json");
        if (!this.database.exists()) {
            try {
                if (!this.database.createNewFile())
                    System.exit(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.databaseData = downloadDatabase();
    }

    @Override
    public void uploadUserData(String userId, UserParameters parameters) {
        this.databaseData.put(userId, parseUserParametersToJsonObject(parameters));
        uploadDatabase(this.databaseData);
    }

    @Override
    public UserParameters downloadUserData(String userId) {
        JsonObject userParametersAsJson = (JsonObject) this.databaseData.get(userId);
        return userParametersAsJson == null
                ? null
                : parseJsonObjectToUserParameters(userParametersAsJson);
    }

    private JsonObject downloadDatabase() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.database.getPath()));
            JsonObject data = (JsonObject) Jsoner.deserialize(reader);
            reader.close();
            return data;
        } catch (IOException | JsonException e) {
            e.printStackTrace();
            System.exit(3);
        }
        return null;
    }

    private void uploadDatabase(JsonObject data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.database.getPath()));
            Jsoner.serialize(data, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    private JsonObject parseUserParametersToJsonObject(UserParameters parameters) {
        JsonObject parametersAsJson = new JsonObject();
        parametersAsJson.put("searchResult", parameters.getSearchResult());
        parametersAsJson.put("filter", parseFilterToJsonObject(parameters.getFilter()));
        parametersAsJson.put("numberOfCurrentFilm", parameters.getNumberOfCurrentFilm());
        parametersAsJson.put("numberOfCurrentPage", parameters.getNumberOfCurrentPage());
        return parametersAsJson;
    }

    private JsonObject parseFilterToJsonObject(Filter filter) {
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

    private UserParameters parseJsonObjectToUserParameters(JsonObject jsonObject) {
        try {
            return new UserParameters(
                    (JsonObject) jsonObject.get("searchResult"),
                    parseJsonObjectToFilter((JsonObject) jsonObject.get("filter")),
                    (int) jsonObject.get("numberOfCurrentFilm"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Filter parseJsonObjectToFilter(JsonObject jsonObject) {
        Filter filter = new Filter();
        filter.setCountries((int[]) jsonObject.get("countries"));
        filter.setGenres((int[]) jsonObject.get("genres"));
        filter.setOrder((String) jsonObject.get("order"));
        filter.setType((String) jsonObject.get("type"));
        filter.setRatingFrom((int) jsonObject.get("ratingFrom"));
        filter.setRatingTo((int) jsonObject.get("ratingTo"));
        filter.setYearFrom((int) jsonObject.get("yearFrom"));
        filter.setYearTo((int) jsonObject.get("yearTo"));
        filter.setPage((int) jsonObject.get("page"));
        return filter;
    }
}
