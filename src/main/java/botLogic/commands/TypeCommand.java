package botLogic.commands;

import botLogic.UserParametersRepository;
import botLogic.UserData;
import userParametersRepository.UserParameters;
import kinopoiskAPI.Filter;

import static java.util.Objects.isNull;

public class TypeCommand {
    public void type(Object argument) {
        UserParameters userParameters = UserParametersRepository.userParametersRepository.get(UserData.getUserId());
        Filter filter = userParameters.getFilter();
        if (isNull(argument))
            filter.setType("");
        else {
            String type = String.valueOf(argument);
            if (type.equals("film"))
                filter.setType("film");
            else if (type.equals("serial"))
                filter.setType("serial");
        }
    }
}
