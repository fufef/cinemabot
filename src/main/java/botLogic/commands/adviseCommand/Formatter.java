package botLogic.commands.adviseCommand;

import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.API;
import parser.Parser;

public class Formatter {
    public static String getInformationAboutFilm(int filmId) {
        JsonObject information = API.getInformationAboutFilmById(filmId);
        if (information == null)
            return "Нет результатов";
        return getInformationInFormattedForm(information);
    }

    private static String getInformationInFormattedForm(JsonObject information) {
        StringBuilder result = new StringBuilder();
        result.append(generateName(information));
        result.append(generateRatings(information));
        return String.valueOf(result);
    }

    private static StringBuilder generateName(JsonObject information) {
        StringBuilder result = new StringBuilder();
        String nameRu = getNameRu(information);
        String nameOriginal = getNameOriginal(information);
        if (nameRu != null && !nameRu.isEmpty()) {
            result.append(nameRu);
            if (nameOriginal != null && !nameOriginal.isEmpty())
                result.append(String.format("\n(%s)", nameOriginal));
        } else
            result.append(nameOriginal);
        result.append("\n");
        return result;
    }

    private static StringBuilder generateRatings(JsonObject information) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(
                "Рейтинг: %.1f (КП), %.1f (IMDb)\n",
                getRatingKinopoisk(information),
                getRatingImdb(information)));
        return result;
    }

    private static String getNameRu(JsonObject information) {
        return (String) information.get("nameRu");
    }

    private static String getNameOriginal(JsonObject information) {
        return (String) information.get("nameOriginal");
    }

    private static double getRatingImdb(JsonObject information) {
        Object ratingImdb = information.get("ratingImdb");
        if (ratingImdb == null)
            return 0.0;
        return Parser.parseToDouble(ratingImdb);
    }

    private static double getRatingKinopoisk(JsonObject information) {
        Object ratingKinopoisk = information.get("ratingKinopoisk");
        if (ratingKinopoisk == null)
            return 0.0;
        return Parser.parseToDouble(ratingKinopoisk);
    }

    private static String getWebUrl(JsonObject information) {
        return (String) information.get("webUrl");
    }
//    private static String getYear(JsonObject information){}
//    private static String getFilmLength(JsonObject information){}
//    private static String getSlogan(JsonObject information){}
//    private static String getDescription(JsonObject information){}
//    private static String getShortDescription(JsonObject information){}
//    private static String getType(JsonObject information){}
//    private static String getStartYear(JsonObject information){}
//    private static String getEndYear(JsonObject information){}
//    private static String getGenres(JsonObject information){}
//    private static String getCountries(JsonObject information){}
//    private static String getRatingMpaa(JsonObject information){}
//    private static String getRatingAgeLimits(JsonObject information){}
}
