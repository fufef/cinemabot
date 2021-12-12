package botLogic.commands;

import botLogic.commands.adviseCommand.formatter.Formatter;
import botLogic.userData.UsersData;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.API;
import userParametersRepository.UserParameters;

import java.math.BigDecimal;

public class NewsCommand {
    public static String release() throws Exception {
        JsonArray films = (JsonArray)API.getLatestReleases().get("releases");
        int numberOfFilm = (int)(Math.random() * films.size());
        int filmId = ((BigDecimal)((JsonObject)(films).get(numberOfFilm)).get("filmId")).intValue();
        StringBuilder message = new StringBuilder("Привет! Смотрю, ты давно не интересовался фильмами! Зацени то, что вышло в этом месяце: \n \n");
        message.append(Formatter.getInformationAboutFilm(filmId));
        return message.toString();
    }
}
