package botLogic.commands;

import botLogic.userData.UsersData;
import kinopoiskAPI.Filter;
import userParametersRepository.UserParameters;

public class TypeCommand {
    public static String type(Object[] arguments) throws Exception {
        String typeOfMovie = arguments.length == 0 ? "all" : (String) arguments[0];
        return type(typeOfMovie);
    }

    private static String type(String typeOfMovie) throws Exception {
        UserParameters userParameters = UsersData.getParametersOfCurrentUser();
        Filter filter = userParameters.getFilter();
        switch (typeOfMovie) {
            case "film" -> filter.setType("FILM");
            case "serial" -> filter.setType("TV_SHOW");
            case "all" -> filter.setType("ALL");
            default -> throw new IllegalArgumentException("Указан некорректный тип фильма");
        }
        UsersData.saveSearchResultOfCurrentUser(filter);
        return String.format("Параметры поиска успешно изменены: тип - %s", typeOfMovie);
    }
}