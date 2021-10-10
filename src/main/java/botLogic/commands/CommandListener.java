package botLogic.commands;

import kinopoiskAPI.Filter;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
//import static kinopoiskAPI.API.getInformationAboutFilmsByFilter;

public class CommandListener {
    //todo сделать что-то с page. например выбирать ее рандомно:
    Filter filter = new Filter();
    final HashMap<String, Integer> allGenres = new HashMap<String, Integer>() {{
        put("комедия", 1);
        put("ужасы", 2);
        //todo нормально сделать
    }};
    final HashMap<String, Integer> allCountries = new HashMap<String, Integer>() {{
        put("Россия", 1);
        put("Австрия", 2);
        //todo нормально сделать
    }};

    @Command(
            name = "/help",
            arguments = "[command]",
            maxArgs = 1,
            description = """
                    Выводит краткую информацию о боте, список команд и их описание.
                    Если указан аргумент [command], выводит справку по указанной команде.""")
    public String help(Object[] arguments) {
        if (arguments.length > 0)
            return HelpCommand.getHelpForCommand(getAllCommands(), pullOutArguments(arguments)[0]);
        return HelpCommand.getHelpForAllCommands(getAllCommands());
    }

    @Command(
            name = "/advise",
            arguments = "",
            maxArgs = 0,
            description = """
                    Бот просит указать жанр и формат, после выдает список найденных им фильмов.
                    По желанию, пользователь может уточнить запрос, указав параметры сортировки.
                    После уточнения запроса бот формирует новые списки.""")
    public String advise(Object[] arguments) {
        return adviseSomeFilm();
    }

    @Command(
            name = "/name",
            arguments = "'название фильма'",
            maxArgs = 1,
            description = """
                    Выводит краткую информацию о боте, список команд и их описание.
                    Вызов команды без указания имени фильма сбрасывает фильтр по имени."""
    )
    public void name(Object[] arguments) {
    }

    @Command(
            name = "/genre",
            arguments = "'название жанра/жанров'",
            description = """
                    Сортировка по жанру/жанрам фильма (жанры указываются через пробел).
                    Вызов команды без указания жанра сбрасывает фильтр по жанрам."""
    )
    public void genre(Object[] arguments) {
        setGenreFilter(arguments);
    }

    @Command(
            name = "/type",
            arguments = "serial | film",
            maxArgs = 1,
            description = """ 
                        /type serial : искать только сериалы
                        /type film : искать только фильмы
                    Вызов команды без указания типа контента сбрасывает фильтр по типу.""")
    public void type(Object argument) {
        setFormatFilter(argument);
    }

    @Command(
            name = "/rating",
            arguments = "r | r r | >r | <r",
            maxArgs = 2,
            description = """
                    Сортировка по рейтингу фильмов.
                        /rating r : поиск фильмов с указанным рейтингом
                        /rating r-r : поиск фильмов с рейтингом в указанном диапазоне
                        /rating >r : поиск фильмов с рейтингом выше указанного
                        /rating <r : поиск фильмов с рейтингом ниже указанного
                    Вызов команды без указания рейтинга сбрасывает фильтр по рейтингу.""")
    public void rating(Object argument) {
        setRatingFilter(argument);
    }

    @Command(
            name = "/year",
            arguments = "y | y y | >y | <y",
            maxArgs = 2,
            description = """
                    Сортировка по году(ам) выхода фильма.
                        /year y : поиск по указанному году
                        /year y-y : поиск по указанному временному промежутку
                        /year >y : поиск фильмов, выпущенных позже указанного года
                        /year <y : поиск фильмов, выпущенных ранее указанного года
                    Вызов команды без указания года(ов) сбрасывает фильтр по году.""")
    public void year(Object argument) {
        setYearFilter(argument);
    }

    @Command(
            name = "/country",
            arguments = "'название страны/стран'",
            description = """
                    Сортировка по странам. Список стран указывается через пробел.
                    Вызов команды без указания страны(ан) сбрасывает фильтр по странам.""")
    public void country(Object[] arguments) {
        setCountryFilter(arguments);
    }

    private void setCountryFilter(Object[] arguments) {
        //??????
//        ArrayList filterCountries = new ArrayList();
//        for (Object arg : arguments) {
//            if (allCountries.containsKey(arg))
//                filterCountries.add(allCountries.get(arg));
//        }
//        filter = new Filter(filterCountries.stream().mapToInt(i -> (int) i).toArray(), filter.genres(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private void setYearFilter(Object argument) {
//        //y | y-y | >y | <y - todo отдельный метод который бы выделял из строки нужный промежуток чисел
//        if (tryParseInt(argument.toString())) {
//            int year = parseInt(argument.toString());
//            filter = new Filter(filter.countries(), filter.genres(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), year, year, filter.page());
//        }
    }

    private void setRatingFilter(Object argument) {
        //r | r-r | >r | <r - todo отдельный метод который бы выделял из строки нужный промежуток чисел
//        if (tryParseInt(argument.toString())) {
//            int rate = parseInt(argument.toString());
//            filter = new Filter(filter.countries(), filter.genres(), filter.order(), filter.type(), rate, rate, filter.yearFrom(), filter.yearTo(), filter.page());
//        }
    }

    private void setFormatFilter(Object argument) {
//        if (argument.toString().equals("serial") || argument.toString().equals("film"))
//            filter = new Filter(filter.countries(), filter.genres(), filter.order(), argument.toString(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private void setGenreFilter(Object[] arguments) {
        //??????
//        ArrayList filterGenres = new ArrayList();
//        for (Object arg : arguments) {
//            if (allGenres.containsKey(arg))
//                filterGenres.add(allGenres.get(arg));
//        }
//        filter = new Filter(filter.countries(), filterGenres.stream().mapToInt(i -> (int) i).toArray(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private String adviseSomeFilm() {
        //????????
//        String filmName = new String();
//        String filmDescription = new String();
//        JSONObject film = kinopoiskAPI.API.getInformationAboutFilmsByFilter(filter);
//        filmDescription = film.toString();
//        return filmDescription;
        return AdviseCommand.advise();
    }

    private String[] pullOutArguments(Object[] arguments) {
        return (String[]) arguments;
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Command> getAllCommands() {
        return Arrays
                .stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Command.class))
                .map(m -> m.getAnnotation(Command.class))
                .collect(Collectors.toList());
    }
}
