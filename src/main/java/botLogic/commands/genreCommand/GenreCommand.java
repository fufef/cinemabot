package botLogic.commands.genreCommand;

import botLogic.userData.UserId;
import botLogic.userData.UsersData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kinopoiskAPI.Filter;
import userParametersRepository.UserParameters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GenreCommand {
    public Map<String,Integer> GenresIdMap;

    public void genre(Object[] arguments) {
        UserParameters userParameters = UsersData.userParametersRepository.getUserData(UserId.getIdOfCurrentUser());
        Filter filter = userParameters.getFilter();
        ArrayList<Integer> addingGenres = new ArrayList<>();
        if (arguments.length == 0)
            filter.setGenres(new int[]{});
        else {
            try {
                GenresIdMap = new ObjectMapper().readValue(new File("\\GenresId"), new TypeReference<Map<String, Integer>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Object arg : arguments) {
                if (GenresIdMap.containsKey(String.valueOf(arg))) {
                    Integer genreId = GenresIdMap.get(String.valueOf(arg));
                    addingGenres.add(genreId);
                }
            }
            filter.addGenres(addingGenres);
        }
    }
}
