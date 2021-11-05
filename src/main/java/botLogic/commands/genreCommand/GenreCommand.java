package botLogic.commands.genreCommand;

import botLogic.commands.CommandException;
import botLogic.userData.UsersData;
import kinopoiskAPI.API;
import kinopoiskAPI.Filter;
import parser.Parser;

import java.util.HashSet;
import java.util.Map;

public class GenreCommand {
    private static final Map<String, Integer> genresIdMap;

    static {
        genresIdMap = API.getGenresId();
    }

    public static void setGenre(String[] arguments) throws Exception {
        if (arguments.length > 0)
            setGenres(arguments);
        else
            resetGenres();
    }

    private static void setGenres(String[] genres) throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.setGenres(getGenresId(genres));
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static int[] getGenresId(String[] genres) {
        HashSet<Integer> addingGenres = new HashSet<>();
        for (String genre : genres) {
            try {
                addingGenres.add(genresIdMap.get(genre));
            } catch (NullPointerException exception) {
                throw new CommandException(String.format("Неизвестный жанр: %s", genre));
            }
        }
        return Parser.parseArrayToArrayOfInt(addingGenres.toArray(Integer[]::new));
    }

    private static void resetGenres() throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.setGenres(new int[0]);
        UsersData.saveSearchResultOfCurrentUser(filter);
    }
}