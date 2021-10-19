package botLogic.commands;

import botLogic.UserParametersRepository;
import botLogic.UserData;
import userParametersRepository.UserParameters;
import kinopoiskAPI.Filter;

import java.util.ArrayList;

public class GenreCommand {
    public void genre(Object[] arguments) {
        UserParameters userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        Filter filter = userParameters.getFilter();
        ArrayList<Integer> addingGenres = new ArrayList<>();
        if (arguments.length == 0)
            filter.setGenres(new int[]{});
        else {
            for (Object arg : arguments) {
                if (ContainsGenre(String.valueOf(arg))) {
                   Integer genreId = 0;
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
