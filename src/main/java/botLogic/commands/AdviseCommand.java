package botLogic.commands;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.Filter;

public class AdviseCommand {
    private static final Filter filter;

    static {
        filter = new Filter();
    }

    public static String advise() {
        /*TODO здесь 18 строка только для примера
         * написать команду по-нормальному
         * реализовать получение настроек поиска пользователя*/
        filter.setYearFrom(2020);
        return generateResponseFrom(
                kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter));
    }

    private static String generateResponseFrom(JsonObject descriptionOfFilm) {
        JsonArray films = (JsonArray) descriptionOfFilm.get("films");
        JsonObject film = (JsonObject) films.get(0);
        long filmId = ((Number) film.get("filmId")).longValue();
        JsonObject fullInfoAboutFilm = kinopoiskAPI.API.getInformationAboutFilmById(filmId);
        return String.format("Название фильма: %s\n", fullInfoAboutFilm.get("nameRu")) +
                String.format("Год выхода: %d\n", ((Number) fullInfoAboutFilm.get("year")).longValue()) +
//                String.format("Рейтинг IMDB: %f\n", (double) fullInfoAboutFilm.get("ratingImdb")) +
                String.format("Описание: %s\n", fullInfoAboutFilm.get("shortDescription"));
    }
}
