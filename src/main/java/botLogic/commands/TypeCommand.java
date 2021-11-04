package botLogic.commands;

import botLogic.userData.UserId;
import botLogic.userData.UsersData;
import kinopoiskAPI.Filter;
import userParametersRepository.UserParameters;

import static java.util.Objects.isNull;

public class TypeCommand {
    public void type(Object argument) {
        UserParameters userParameters = UsersData.userParametersRepository.getUserData(UserId.getIdOfCurrentUser());
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
