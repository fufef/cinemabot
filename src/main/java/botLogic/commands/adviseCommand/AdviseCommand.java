package botLogic.commands.adviseCommand;

import botLogic.Database;
import botLogic.UserData;
import com.github.cliftonlabs.json_simple.JsonObject;
import database.UserParameters;
import parser.Parser;
import kinopoiskAPI.Filter;

public class AdviseCommand {
    public static String advise() throws Exception {
        UserParameters userParameters = Database.database.downloadUserData(UserData.getUserId());
        if (userParameters == null) {
            registerUser();
            userParameters = Database.database.downloadUserData(UserData.getUserId());
        }
        return getNextFilm(userParameters);
    }

    private static String getNextFilm(UserParameters userParameters) throws Exception {
        if (userParameters.getNumberOfCurrentFilm() > userParameters.getCountOfFilmsOnCurrentPage()) {
            if (userParameters.getNumberOfCurrentPage() >= userParameters.getPagesCount()) {
                resetSearch(userParameters);
                return "Предложены все возможные варианты при заданных параметрах поиска\n" +
                        "При повторном вызове команды будут рекомендоваться уже предлагавшиеся фильмы";
            }
            goToNextPage(userParameters);
            userParameters = Database.database.downloadUserData(UserData.getUserId());
        }
        JsonObject film = userParameters.getCurrentFilm();
        userParameters.nextFilm();
        Database.database.uploadUserData(UserData.getUserId(), userParameters);
        return Formatter.getInformationAboutFilm(
                Parser.parseToInt(film.get("filmId")));
    }

    private static void goToNextPage(UserParameters userParameters) throws Exception {
        Filter filter = userParameters.getFilter();
        filter.setPage(filter.getPage() + 1);
        updateSearchResultInDatabase(filter);
    }

    private static void resetSearch(UserParameters userParameters) throws Exception {
        Filter filter = userParameters.getFilter();
        filter.setPage(1);
        updateSearchResultInDatabase(filter);
    }

    private static void registerUser() throws Exception {
        updateSearchResultInDatabase(new Filter());
    }

    private static void updateSearchResultInDatabase(Filter filter) throws Exception {
        JsonObject searchResult = kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter);
        UserParameters userParameters = new UserParameters(searchResult, filter, 1);
        Database.database.uploadUserData(UserData.getUserId(), userParameters);
    }
}
