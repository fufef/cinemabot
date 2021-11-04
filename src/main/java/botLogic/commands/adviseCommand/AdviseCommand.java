package botLogic.commands.adviseCommand;

import botLogic.userData.UsersData;
import userParametersRepository.Repository;
import botLogic.userData.UserId;
import com.github.cliftonlabs.json_simple.JsonObject;
import userParametersRepository.UserParameters;
import parser.Parser;
import kinopoiskAPI.Filter;

public class AdviseCommand {
    public static String advise() throws Exception {
        UserParameters userParameters = getParametersOfCurrentUser();
        if (userParameters == null) {
            registerUser();
            userParameters = getParametersOfCurrentUser();
        }
        return getNextFilm(userParameters);
    }

    private static String getNextFilm(UserParameters userParameters) throws Exception {
        JsonObject filmInfo = userParameters.getCurrentFilm();
        if (filmInfo == null)
            return "Фильмы не найдены";
        int filmId = Parser.parseToInt(filmInfo.get("filmId"));
        String descriptionOfFilm = Formatter.getInformationAboutFilm(filmId);
        goToNextFilm(userParameters);
        return descriptionOfFilm;
    }

    private static void goToNextFilm(UserParameters userParameters) throws Exception {
        if (userParameters.nextFilm())
            saveParametersOfCurrentUser(userParameters);
        else if (userParameters.isLastPageOpen())
            resetSearch(userParameters);
        else
            goToNextPage(userParameters);
    }

    private static void goToNextPage(UserParameters userParameters) throws Exception {
        Filter filter = userParameters.getFilter();
        filter.setPage(filter.getPage() + 1);
        saveSearchResultOfCurrentUser(filter);
    }

    private static void resetSearch(UserParameters userParameters) throws Exception {
        Filter filter = userParameters.getFilter();
        filter.setPage(1);
        saveSearchResultOfCurrentUser(filter);
    }

    private static void registerUser() throws Exception {
        saveSearchResultOfCurrentUser(new Filter());
    }

    private static void saveSearchResultOfCurrentUser(Filter filter) throws Exception {
        JsonObject searchResult = kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter);
        UserParameters userParameters = new UserParameters(searchResult, filter, 1);
        saveParametersOfCurrentUser(userParameters);
    }

    private static void saveParametersOfCurrentUser(UserParameters userParameters) {
        UsersData.userParametersRepository.saveUserData(UserId.getIdOfCurrentUser(), userParameters);
    }

    private static UserParameters getParametersOfCurrentUser() {
        return UsersData.userParametersRepository.getUserData(UserId.getIdOfCurrentUser());
    }
}
