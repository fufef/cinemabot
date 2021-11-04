package botLogic.commands;

import botLogic.userData.UserId;
import botLogic.userData.UsersData;
import kinopoiskAPI.Filter;
import userParametersRepository.UserParameters;

import static java.util.Objects.isNull;
/// TODO: 04.11.2021
public class RatingCommand {
    public void rating(Object argument) {
        UserParameters userParameters = UsersData.getParametersOfCurrentUser();
        Filter filter = userParameters.getFilter();
        if (isNull(argument)){
            filter.setRatingTo(10);
            filter.setRatingFrom(0);
        }
        else {
            String arg = String.valueOf(argument);
            int from = 0;
            int to = 10;
            if (arg.contains("-")){
                int ind = arg.indexOf('-');
                from = Integer.parseInt(arg.substring(0, ind));
                to = Integer.parseInt(arg.substring(ind + 1));
            }
            else if (arg.contains(">")){
                from = Integer.parseInt(arg.substring(1));
            }
            else if (arg.contains("<")){
                to = Integer.parseInt(arg.substring(1));
            }
            filter.setRatingTo(to);
            filter.setRatingFrom(from);
        }
    }
}
