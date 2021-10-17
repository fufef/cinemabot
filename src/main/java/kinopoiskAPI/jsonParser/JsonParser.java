package kinopoiskAPI.jsonParser;

import org.json.JSONObject;

import java.text.ParseException;

public class JsonParser {
    public static JSONObject Parse(String jsonString) throws ParseException {
        return new JSONObject(jsonString);
    }
}
