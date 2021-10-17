package kinopoiskAPI.jsonParser;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.text.ParseException;

public class JsonParser {
    public static JsonObject Parse(String jsonString) throws ParseException {
        return Jsoner.deserialize(jsonString, new JsonObject());
    }
}
