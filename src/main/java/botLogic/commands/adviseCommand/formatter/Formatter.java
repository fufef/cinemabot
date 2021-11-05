package botLogic.commands.adviseCommand.formatter;

import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.API;
import parser.Parser;

import java.util.Arrays;
import java.util.Locale;

import static botLogic.commands.adviseCommand.formatter.ParserOfFilmInfo.*;

public class Formatter {
    public static String getInformationAboutFilm(int filmId) {
        JsonObject information = API.getInformationAboutFilmById(filmId);
        if (information == null)
            return "Нет результатов";
        return getInformationInFormattedForm(information);
    }

    private static String getInformationInFormattedForm(JsonObject information) {
        String[] description = Arrays.stream(
                        new String[]{
                                String.join(", ", generateName(information), generateAgeRatings(information)),
                                generateTypeAndYear(information),
                                generateCountries(information),
                                generateGenres(information),
                                generateRatings(information),
                                generateDescription(information),
                                generateWebUrl(information)
                        })
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        return String.join("\n", description);
    }

    private static String generateName(JsonObject information) {
        StringBuilder result = new StringBuilder();
        String nameRu = getNameRu(information);
        String nameOriginal = getNameOriginal(information);
        if (nameRu != null && !nameRu.isEmpty())
            result.append(nameRu);
        if (nameOriginal != null && !nameOriginal.isEmpty())
            result.append(String.format(" (%s)", nameOriginal));
        return result.isEmpty() ? "Без названия" : (String.valueOf(result)).strip();
    }

    private static String generateRatings(JsonObject information) {
        return String.format(
                "Рейтинг: %.1f (КП), %.1f (IMDb)",
                getRatingKinopoisk(information),
                getRatingImdb(information));
    }

    private static String generateDescription(JsonObject information) {
        String description = getDescription(information);
        if (description == null || description.isEmpty())
            description = getShortDescription(information);
        if (description == null || description.isEmpty())
            return "Описание отсутствует";
        return description;
    }

    private static String generateTypeAndYear(JsonObject information) {
        StringBuilder result = new StringBuilder();
        String type = getType(information);
        if (type != null)
            result.append(type);
        String year = generateYear(information);
        if (!year.isEmpty())
            result.append(", ").append(year);
        return result.length() > 0 ? (String.valueOf(result)).trim() : "";
    }

    private static String generateYear(JsonObject information) {
        Object startYear = information.get("startYear");
        if (startYear == null) {
            Object year = information.get("year");
            return year == null ? "" : String.valueOf(Parser.parseObjectToInt(year));
        }
        Object endYear = information.get("endYear");
        return String.format(
                "%s - %s",
                startYear,
                (endYear == null ? "..." : String.valueOf(endYear)));
    }

    private static String generateAgeRatings(JsonObject information) {
        StringBuilder result = new StringBuilder();
        String ratingAgeLimits = getRatingAgeLimits(information);
        if (ratingAgeLimits != null)
            return String.valueOf(result.append(ratingAgeLimits.substring(3)).append("+"));
        String ratingMPAA = getRatingMPAA(information);
        return ratingMPAA != null
                ? String.valueOf(result.append(ratingMPAA)).toUpperCase(Locale.ROOT)
                : "";
    }

    private static String generateCountries(JsonObject information) {
        String[] countries = getCountries(information);
        return countries.length != 0
                ? "Страны: " + String.join(", ", countries)
                : "";
    }

    private static String generateGenres(JsonObject information) {
        String[] genres = getGenres(information);
        return genres.length != 0
                ? "Жанры: " + String.join(", ", genres)
                : "";
    }

    private static String generateWebUrl(JsonObject information) {
        String url = getWebUrl(information);
        return url != null ? String.format("Ссылка на страницу КиноПоиска: %s", url) : "";
    }
}
