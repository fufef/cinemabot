package botLogic.commands.adviseCommand.formatter;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import parser.Parser;

import java.util.Locale;

public class ParserOfFilmInfo {
    public static String getNameRu(JsonObject filmInfo) {
        return (String) filmInfo.get("nameRu");
    }

    public static String getNameOriginal(JsonObject filmInfo) {
        return (String) filmInfo.get("nameOriginal");
    }

    public static double getRatingImdb(JsonObject filmInfo) {
        Object ratingImdb = filmInfo.get("ratingImdb");
        if (ratingImdb == null)
            return 0.0;
        return Parser.parseObjectToDouble(ratingImdb);
    }

    public static double getRatingKinopoisk(JsonObject filmInfo) {
        Object ratingKinopoisk = filmInfo.get("ratingKinopoisk");
        if (ratingKinopoisk == null)
            return 0.0;
        return Parser.parseObjectToDouble(ratingKinopoisk);
    }

    public static String getDescription(JsonObject filmInfo) {
        return (String) filmInfo.get("description");
    }

    public static String getShortDescription(JsonObject filmInfo) {
        return (String) filmInfo.get("shortDescription");
    }

    public static String getWebUrl(JsonObject filmInfo) {
        return (String) filmInfo.get("webUrl");
    }

    public static String getType(JsonObject filmInfo) {
        String type = (String) filmInfo.get("type");
        if (type == null) return null;
        return switch (type.toLowerCase(Locale.ROOT)) {
            case "film" -> "Фильм";
            case "tv_serial" -> "Сериал";
            default -> type;
        };
    }

    public static String getRatingMPAA(JsonObject filmInfo) {
        return (String) filmInfo.get("ratingMpaa");
    }

    public static String getRatingAgeLimits(JsonObject filmInfo) {
        return (String) filmInfo.get("ratingAgeLimits");
    }

    public static String[] getCountries(JsonObject filmInfo) {
        return makeArrayFromByKey((JsonArray) filmInfo.get("countries"), "country");
    }

    public static String[] getGenres(JsonObject filmInfo) {
        return makeArrayFromByKey((JsonArray) filmInfo.get("genres"), "genre");
    }

    private static String[] makeArrayFromByKey(JsonArray array, String key) {
        String[] result = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JsonObject item = (JsonObject) array.get(i);
            result[i] = (String) item.get(key);
        }
        return result;
    }
}
