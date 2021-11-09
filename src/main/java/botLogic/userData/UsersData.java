package botLogic.userData;

import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.Filter;
import userParametersRepository.Repository;
import userParametersRepository.UserParameters;
import userParametersRepository.UserParametersRepository;

public class UsersData {
    private static UserParametersRepository userParametersRepository;

    public static void initializeRepository(UserParametersRepository userParametersRepository) {
        UsersData.userParametersRepository = userParametersRepository;
    }

    public static void saveParametersOfCurrentUser(UserParameters userParameters) {
        check();
        userParametersRepository.saveUserData(UserId.getIdOfCurrentUser(), userParameters);
    }

    public static UserParameters getParametersOfCurrentUser() {
        check();
        return userParametersRepository.getUserData(UserId.getIdOfCurrentUser());
    }

    public static void saveSearchResultOfCurrentUser(Filter filter) throws Exception {
        JsonObject searchResult = kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter);
        UserParameters userParameters = new UserParameters(searchResult, filter, 1);
        saveParametersOfCurrentUser(userParameters);
    }

    private static void check() {
        checkRepository();
        checkUserId();
    }

    private static void checkUserId() {
        if (UserId.getIdOfCurrentUser() == null)
            throw new NullPointerException("Attempt to access the repository of user parameters, but UserId is null");
    }

    private static void checkRepository() {
        if (userParametersRepository == null)
            throw new NullPointerException(
                    "Attempt to access the user parameters repository, but the repository was not initialized");
    }
}