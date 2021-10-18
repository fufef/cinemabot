package kinopoiskAPI.jsonParser;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class JsonParser {
    public static JsonObject Parse(String jsonString) {
        return Jsoner.deserialize(jsonString, new JsonObject());
    }
}
