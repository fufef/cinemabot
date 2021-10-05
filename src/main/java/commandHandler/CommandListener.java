package commandHandler;

import kinopoiskAPI.Filter;
import org.json.simple.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static kinopoiskAPI.API.getInformationAboutFilmsByFilter;

// ICommand { }
// DI Container

public class CommandListener {
    //сделать что-то с page. например выбирать ее рандомно:
    Filter filter = new Filter(null, null, null,null, 0, 10, 1800, 2045, 1);
    HashMap<String, Integer> allGenres = new HashMap<String, Integer>() {{
        put("комедия", 1);
        put("ужасы", 2);
        //todo нормально сделать
    }};
    HashMap<String, Integer> allCountries = new HashMap<String, Integer>() {{
        put("Россия", 1);
        put("Австрия", 2);
        //todo нормально сделать
    }};

    @Command(
            name = "/help",
            arguments = "[command]",
            maxArgs = 1,
            description = "Выводит справку по командам")
    public String help(Object[] arguments) {
        if (arguments.length > 0)
            return helpForCommand(pullOutArguments(arguments)[0]);
        return helpForAllCommands();
    }

    @Command(
            name = "/next",
            arguments = "[command]",
            maxArgs = 0,
            description = "Если в списке нет интересных для пользователя фильмов, он может попросить выдать еще подборку (список) фильмов.")
    public String next() {
        return adviseSomeFilm();
    }

    @Command(
            name = "/advise",
            arguments = "[command]",
            maxArgs = 0,
            description = "Бот просит указать жанр и формат, после выдает список найденных им фильмов. По желанию, пользователь может уточнить запрос, указав параметры сортировки. После уточнения запроса бот формирует новые списки.")
    public String advise() {
        return adviseSomeFilm();
    }

    @Command(
            name = "/genre",
            arguments = "[command]",
            description = "Сортировка по жанру/жанрам фильма. Если бот встречает неизвестные ему жанры, он уведомляет об этом пользователя, предлагая справку по поддерживаемым жанрам")
    public void genre(Object[] arguments) {
        setGenreFilter(arguments);
    }

    @Command(
            name = "/format",
            arguments = "[command]",
            maxArgs = 1,
            description = "Выбор формата: serial или film")
    public void format(Object argument) {
        setFormatFilter(argument);
    }

    @Command(
            name = "/rating",
            arguments = "[command]",
            maxArgs = 1,
            description = "Сортировка по рейтингу фильма")
    public void rating(Object argument) {
        setRatingFilter(argument);
    }

    @Command(
            name = "/year",
            arguments = "[command]",
            maxArgs = 1,
            description = "Сортировка по году(ам) выхода фильма")
    public void year(Object argument) {
        setYearFilter(argument);
    }

    @Command(
            name = "/country",
            arguments = "[command]",
            description = "Сортировка по странам")
    public void country(Object[] arguments) {
        setCountryFilter(arguments);
    }

    private void setCountryFilter(Object[] arguments){
        //??????
        ArrayList filterCountries = new ArrayList();
        for (Object arg: arguments) {
            if  (allCountries.containsKey(arg))
                filterCountries.add(allCountries.get(arg));
        }
        filter = new Filter(filterCountries.stream().mapToInt(i -> (int) i).toArray(), filter.genres(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private void setYearFilter(Object argument){
        //y | y-y | >y | <y - todo отдельный метод который бы выделял из строки нужный промежуток чисел
        if (tryParseInt(argument.toString())) {
            int year = parseInt(argument.toString());
            filter = new Filter(filter.countries(), filter.genres(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), year, year, filter.page());
        }
    }

    private void setRatingFilter(Object argument){
        //r | r-r | >r | <r - todo отдельный метод который бы выделял из строки нужный промежуток чисел
        if (tryParseInt(argument.toString())) {
            int rate = parseInt(argument.toString());
            filter = new Filter(filter.countries(), filter.genres(), filter.order(), filter.type(), rate, rate, filter.yearFrom(), filter.yearTo(), filter.page());
        }
    }

    private void setFormatFilter(Object argument){
        if (argument.toString().equals("serial") || argument.toString().equals("film"))
            filter = new Filter(filter.countries(), filter.genres(), filter.order(), argument.toString(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private void setGenreFilter(Object[] arguments){
        //??????
        ArrayList filterGenres = new ArrayList();
        for (Object arg: arguments) {
            if  (allGenres.containsKey(arg))
                filterGenres.add(allGenres.get(arg));
        }
        filter = new Filter(filter.countries(), filterGenres.stream().mapToInt(i -> (int) i).toArray(), filter.order(), filter.type(), filter.ratingFrom(), filter.ratingTo(), filter.yearFrom(), filter.yearTo(), filter.page());
    }

    private String adviseSomeFilm() {
        //????????
        String filmName = new String();
        String filmDescription = new String();
        JSONObject film = getInformationAboutFilmsByFilter(filter);
        filmDescription = film.toString();
        return filmDescription;
    }

    private String helpForCommand(String commandName) {
        StringBuilder descriptions = new StringBuilder();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                if (!command.name().equals(commandName))
                    continue;
                descriptions
                        .append(command.name()).append(" ")
                        .append(command.arguments()).append(" - ")
                        .append(command.description());
                break;
            }
        }
        if (descriptions.isEmpty())
            return String.format("Неизвестная команда %s", commandName);
        return descriptions.toString();
    }

    private String helpForAllCommands() {
        StringBuilder descriptions = new StringBuilder();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                descriptions
                        .append(command.name()).append(" ")
                        .append(command.arguments()).append(" - ")
                        .append(command.description()).append("\n");
            }
        }
        return descriptions.toString();
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
}
