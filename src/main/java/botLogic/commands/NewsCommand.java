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
        UserParameters userParameters = UsersData.getParametersOfCurrentUser();
        JsonArray films = (JsonArray)API.getLatestReleases().get("releases");
        int filmId = ((BigDecimal)((JsonObject)(films).get(0)).get("filmId")).intValue();
        return Formatter.getInformationAboutFilm(filmId);
    }
}
