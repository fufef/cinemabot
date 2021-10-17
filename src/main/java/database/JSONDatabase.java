package database;

import com.github.cliftonlabs.json_simple.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONDatabase implements Database {
    private final String pathToDatabase = "src/main/java/database/database.json";
    private File database;

    public JSONDatabase() {
        database = new File(pathToDatabase);
        try {
            if (!database.createNewFile())
                System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        JsonObject userFilter = new JsonObject();
//        userFilter.put("name", "Bob");
//        userFilter.put("age", 35);
//        Files.write(Paths.get(pathToDatabase), userFilter.toJSONString().getBytes());
    }

    @Override
    public void pushData(String userId, UserParameters parameters) {
        JsonObject parametersAsJSON = parseUserParametersToJSONObject(parameters);

//        Files.write(Paths.get(pathToDatabase), parametersAsJSON.toJSONString().getBytes());
    }

    @Override
    public UserParameters pullData(String userId) {
        return null;
    }

    private JsonObject parseUserParametersToJSONObject(UserParameters parameters) {
        JsonObject parametersAsJSON = new JsonObject();
        parametersAsJSON.put("numberOfCurrentFilm", parameters.getNumberOfCurrentFilm());
        parametersAsJSON.put("countOfFilmsOnCurrentPage", parameters.getCountOfFilmsOnCurrentPage());
        parametersAsJSON.put("numberOfCurrentPage", parameters.getNumberOfCurrentPage());
        parametersAsJSON.put("pagesCount", parameters.getPagesCount());
//      TODO  parametersAsJSON.put("filter", new JsonObject(parameters.getFilter()));
        return parametersAsJSON;
    }
}
