package botLogic.commands.countryCommand;

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
/// TODO: 04.11.2021
public class CountryCommand {
    public Map<String,Integer> CountriesIdMap;

    public void country(Object[] arguments) {
        UserParameters userParameters = UsersData.getParametersOfCurrentUser();
        Filter filter = userParameters.getFilter();
        ArrayList<Integer> addingCountries = new ArrayList<>();
        if (arguments.length == 0)
            filter.setCountries(new int[]{});
        else {
            try {
                CountriesIdMap = new ObjectMapper().readValue(new File("\\CountriesId"), new TypeReference<Map<String, Integer>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Object arg : arguments) {
                if (CountriesIdMap.containsKey(String.valueOf(arg))) {
                    Integer countryId = CountriesIdMap.get(String.valueOf(arg));
                    addingCountries.add(countryId);
                }
            }
            filter.addCountries(addingCountries);
        }
    }
}
