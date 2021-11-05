package botLogic.commands;

import botLogic.userData.UsersData;
import kinopoiskAPI.Filter;

public class RatingCommand {
    public static void setRating(String[] arguments) throws Exception {
        if (arguments.length == 0)
            resetRatings();
        else {
            if (arguments.length == 1)
                setRating(arguments[0]);
            else
                setRatings(arguments[0], arguments[1]);
        }
    }

    private static void resetRatings() throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.resetRatings();
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static void setRatings(String ratingFrom, String ratingTo) throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.setRatingFrom(tryParseRatingToInt(ratingFrom));
        filter.setRatingTo(tryParseRatingToInt(ratingTo));
        checkCorrectnessOfRatings(filter);
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static void setRating(String rating) throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        switch (rating.charAt(0)) {
            case '>' -> filter.setRatingFrom(tryParseRatingToInt(rating.substring(1)));
            case '<' -> filter.setRatingTo(tryParseRatingToInt(rating.substring(1)));
            default -> throw new CommandException("Рейтинг указан некорректно");
        }
        checkCorrectnessOfRatings(filter);
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static void checkCorrectnessOfRatings(Filter filter) {
        if (filter.getRatingFrom() > filter.getRatingTo())
            throw new CommandException("Указанный минимальный рейтинг больше указанного максимального");
    }

    private static int tryParseRatingToInt(String rating) {
        try {
            int result = Integer.parseInt(rating);
            if (result < 0 || result > 10)
                throw new CommandException("Рейтинг должен находиться в пределах 0-10");
            return result;
        } catch (NumberFormatException exception) {
            throw new CommandException("Рейтинг должен быть указан числом");
        }
    }
}
