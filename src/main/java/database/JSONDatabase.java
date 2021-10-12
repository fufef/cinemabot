package database;

import kinopoiskAPI.Filter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONDatabase implements Database {
    String pathToDatabase = "src/main/java/database/database.json";
    JSONObject database;

    public JSONDatabase() throws IOException {
        JSONObject userFilter = new JSONObject();
        userFilter.put("name", "Stackabuser");
        userFilter.put("age", 35);

        JSONArray messages = new JSONArray();
        messages.add("Hey!");
        messages.add("What's up?!");

        userFilter.put("messages", messages);
        Files.write(Paths.get(pathToDatabase), userFilter.toJSONString().getBytes());


    }

    @Override
    public void pushData(String userId, Filter filter) throws IOException {
        JSONObject userFilter = new JSONObject();
        userFilter.put(userId, filter);
        Files.write(Paths.get(pathToDatabase), userFilter.toJSONString().getBytes());
    }

    @Override
    public Filter pullData(String userId) {
        return null;
    }
}
