package database;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONDatabase implements Database {
    private final String pathToDatabase = "src/main/java/database/database.json";
    private File database;

    public JSONDatabase() throws IOException {
        /*JSONObject userFilter = new JSONObject();
        userFilter.put("name", "Stackabuser");
        userFilter.put("age", 35);

        JSONArray messages = new JSONArray();
        messages.add("Hey!");
        messages.add("What's up?!");

        userFilter.put("messages", messages);
        Files.write(Paths.get(pathToDatabase), userFilter.toJSONString().getBytes());*/
        database = new File(pathToDatabase);
    }

    @Override
    public void pushData(String userId, UserParameters parameters) throws IOException {
        JSONObject parametersAsJSON = new JSONObject();

        parametersAsJSON.put(userId, parameters);
//        Files.write(Paths.get(pathToDatabase), parametersAsJSON.toJSONString().getBytes());
    }

    @Override
    public UserParameters pullData(String userId) {
        return null;
    }

    private JSONObject parseUserParametersToJSONObject(UserParameters parameters) {
        JSONObject parametersAsJSON = new JSONObject();
        parametersAsJSON.put("numberOfCurrentFilm", parameters.getNumberOfCurrentFilm());
        parametersAsJSON.put("countOfFilmsOnCurrentPage", parameters.getCountOfFilmsOnCurrentPage());
        parametersAsJSON.put("numberOfCurrentPage", parameters.getNumberOfCurrentPage());
        parametersAsJSON.put("pagesCount", parameters.getPagesCount());

        return parametersAsJSON;
    }
}
