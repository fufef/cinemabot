package botLogic.commands;

import kinopoiskAPI.Filter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AdviseCommand {
    private static final Filter filter;

    static {
        filter = new Filter();
    }

    public static String advise() {
        /*TODO здесь 17 строка только для примера
         * написать команду по-нормальному
         * реализовать получение настроек поиска пользователя*/
        filter.setYearFrom(2020);
        return generateResponseFrom(
                kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter));
    }

    private static String generateResponseFrom(JSONObject descriptionOfFilm) {
        JSONArray films = (JSONArray) descriptionOfFilm.get("films");
        JSONObject film = (JSONObject) films.get(0);
        Long filmId = (Long) film.get("filmId");
        JSONObject fullInfoAboutFilm = kinopoiskAPI.API.getInformationAboutFilmById(filmId.intValue());
        return String.format("Название фильма: %s\n", fullInfoAboutFilm.get("nameRu")) +
                String.format("Год выхода: %d\n", (Long) fullInfoAboutFilm.get("year")) +
                String.format("Рейтинг IMDB: %f\n", (Double) fullInfoAboutFilm.get("ratingImdb")) +
                String.format("Описание: %s\n", fullInfoAboutFilm.get("shortDescription"));
    }
}
