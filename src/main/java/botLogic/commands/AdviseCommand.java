package botLogic.commands;

import kinopoiskAPI.Filter;
import org.json.JSONArray;
import org.json.JSONObject;

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

    private static String generateResponseFrom(JSONObject descriptionOfFilm) {
        JSONArray films = (JSONArray) descriptionOfFilm.get("films");
        JSONObject film = (JSONObject) films.get(0);
        long filmId = ((Number) film.get("filmId")).longValue();
        JSONObject fullInfoAboutFilm = kinopoiskAPI.API.getInformationAboutFilmById(filmId);
        return String.format("Название фильма: %s\n", fullInfoAboutFilm.get("nameRu")) +
                String.format("Год выхода: %d\n", ((Number) fullInfoAboutFilm.get("year")).longValue()) +
//                String.format("Рейтинг IMDB: %f\n", (double) fullInfoAboutFilm.get("ratingImdb")) +
                String.format("Описание: %s\n", fullInfoAboutFilm.get("shortDescription"));
    }
}
