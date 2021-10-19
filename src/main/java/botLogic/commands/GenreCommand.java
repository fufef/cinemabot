package botLogic.commands;

import botLogic.Database;
import botLogic.UserData;
import database.UserParameters;
import kinopoiskAPI.Filter;

import java.util.ArrayList;

public class GenreCommand {
    public void genre(Object[] arguments) {
        UserParameters userParameters = Database.database.downloadUserData(UserData.getUserId());
        Filter filter = userParameters.getFilter();
        ArrayList<Integer> addingGenres = new ArrayList<>();
        if (arguments.length == 0)
            filter.setGenres(new int[]{});
        else {
            for (Object arg : arguments) {
                if (ContainsGenre(String.valueOf(arg))) {
                   Integer genreId = 0; //todo
                    addingGenres.add(genreId);
                }
            }
            //filter.addGenre(addingGenres.toArray(Integer[]::new));
        }
    }

    private boolean ContainsGenre(String arg) {
        return true; //
    }
}
