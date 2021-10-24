package botLogic.commands;

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

public class NameCommand {
    public void name(Object[] arguments) {
        UserParameters userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        Filter filter = userParameters.getFilter();
        if (arguments.length == 0){
        }
        else {

        }
    }
}
