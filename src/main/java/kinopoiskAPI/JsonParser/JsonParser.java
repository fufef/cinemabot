package kinopoiskAPI.JsonParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    public static JSONObject Parse(String jsonString) throws ParseException {
        return  (JSONObject) new JSONParser().parse(jsonString);
    }
}
