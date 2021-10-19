package botLogic.commands.adviseCommand;

import botLogic.UserParametersRepository;
import botLogic.UserData;
import com.github.cliftonlabs.json_simple.JsonObject;
import userParametersRepository.UserParameters;
import parser.Parser;
import kinopoiskAPI.Filter;

public class AdviseCommand {
    public static String advise() throws Exception {
        UserParameters userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        if (userParameters == null) {
            registerUser();
            userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        }
        return getNextFilm(userParameters);
    }

    private static String getNextFilm(UserParameters userParameters) throws Exception {
        JsonObject film = userParameters.getCurrentFilm();
        if (!userParameters.nextFilm()){
            if (userParameters.getNumberOfCurrentPage() >= userParameters.getPagesCount()) {
                resetSearch(userParameters);
                return "Предложены все возможные варианты при заданных параметрах поиска\n" +
                        "При повторном вызове команды будут рекомендоваться уже предлагавшиеся фильмы";
            }
            goToNextPage(userParameters);
            userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        }
        UserParametersRepository.userParametersRepository.save(UserData.getUserId(), userParameters);
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
        UserParametersRepository.userParametersRepository.save(UserData.getUserId(), userParameters);
    }
}
