package botLogic.commands.countryCommand;

import botLogic.UserParametersRepository;
import botLogic.UserData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import userParametersRepository.UserParameters;
import kinopoiskAPI.Filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CountryCommand {
    public Map<String,Integer> CountriesIdMap;

    public void country(Object[] arguments) {
        UserParameters userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
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
