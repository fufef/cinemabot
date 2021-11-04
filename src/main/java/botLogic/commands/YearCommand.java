package botLogic.commands;

import botLogic.userData.UsersData;
import kinopoiskAPI.Filter;

public class YearCommand {
    public static void year(String[] arguments) throws Exception {
        if (arguments.length == 0)
            resetYears();
        else {
            if (arguments.length == 1)
                setYear(arguments[0]);
            else
                setYears(arguments[0], arguments[1]);
        }
    }

    private static void resetYears() throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.resetYears();
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static void setYears(String yearFrom, String yearTo) throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        filter.setYearFrom(tryParseYearToInt(yearFrom));
        filter.setYearTo(tryParseYearToInt(yearTo));
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static void setYear(String year) throws Exception {
        Filter filter = UsersData.getParametersOfCurrentUser().getFilter();
        switch (year.charAt(0)) {
            case '>' -> filter.setYearFrom(tryParseYearToInt(year.substring(1)));
            case '<' -> filter.setYearTo(tryParseYearToInt(year.substring(1)));
            default -> throw new IllegalArgumentException("Год указан некорректно");
        }
        UsersData.saveSearchResultOfCurrentUser(filter);
    }

    private static int tryParseYearToInt(String year) {
        try {
            int result = Integer.parseInt(year);
            if (result < 0)
                throw new IllegalArgumentException("Год не может быть отрицательным");
            return result;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Год должен быть указан числом");
        }
    }
}
