package botLogic.commands;

import botLogic.commands.adviseCommand.AdviseCommand;
import botLogic.commands.countryCommand.CountryCommand;
import botLogic.commands.genreCommand.GenreCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandListener {
    @Command(
            name = "/help",
            arguments = "[command]",
            maxArgs = 1,
            description = """
                    Выводит краткую информацию о боте, список команд и их описание
                    Если указан аргумент [command], выводит справку по указанной команде""")
    public String help(Object[] arguments) {
        try {
            if (arguments.length > 0)
                return HelpCommand.getHelpForCommand(getAllCommands(), (String) (arguments)[0]);
            return HelpCommand.getHelpForAllCommands(getAllCommands());
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/help", exception.getMessage());
        }
    }

    @Command(
            name = "/advise",
            arguments = "",
            maxArgs = 0,
            description = """
                    Выдает случайный фильм, удовлетворяющий фильтрам поиска""")
    public String advise(Object[] arguments) {
        try {
            return AdviseCommand.advise();
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/advise", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/advise");
        }
    }

    @Command(
            name = "/type",
            arguments = "serial | film",
            maxArgs = 1,
            description = """ 
                    /type serial : искать только сериалы
                    /type film : искать только фильмы
                    Вызов команды без указания типа контента сбрасывает фильтр по типу""")
    public String type(Object[] arguments) {
        try {
            TypeCommand.setType((String[]) arguments);
            return notifyAboutSuccessfulResult("/type");
        } catch (IllegalArgumentException exception) {
            return notifyAboutUnsuccessfulResult("/type", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/type");
        }
    }

    @Command(
            name = "/year",
            arguments = "y | y y | >y | <y",
            maxArgs = 2,
            description = """
                    Сортировка по году(ам) выхода фильма
                        /year y : поиск по указанному году
                        /year y y : поиск по указанному временному промежутку
                        /year >y : поиск фильмов, выпущенных позже указанного года
                        /year <y : поиск фильмов, выпущенных ранее указанного года
                    Вызов команды без указания года(ов) сбрасывает фильтр по году""")
    public String year(Object[] arguments) {
        try {
            YearCommand.setYear((String[]) arguments);
            return notifyAboutSuccessfulResult("/year");
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/year", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/year");
        }
    }

    @Command(
            name = "/rating",
            arguments = "r | r r | >r | <r",
            maxArgs = 2,
            description = """
                    Сортировка по рейтингу фильмов.
                        /rating r : поиск фильмов с указанным рейтингом
                        /rating r r : поиск фильмов с рейтингом в указанном диапазоне
                        /rating >r : поиск фильмов с рейтингом выше указанного
                        /rating <r : поиск фильмов с рейтингом ниже указанного
                    Вызов команды без указания рейтинга сбрасывает фильтр по рейтингу""")
    public String rating(Object[] arguments) {
        try {
            RatingCommand.setRating((String[]) arguments);
            return notifyAboutSuccessfulResult("/rating");
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/rating", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/rating");
        }
    }

    @Command(
            name = "/genre",
            arguments = "'название жанра/жанров'",
            description = """
                    Сортировка по жанру/жанрам фильма (жанры указываются через пробел)
                    Вызов команды без указания жанра сбрасывает фильтр по жанрам"""
    )
    public String genre(Object[] arguments) {
        try {
            GenreCommand.setGenre((String[]) arguments);
            return notifyAboutSuccessfulResult("/genre");
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/genre", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/genre");
        }
    }

    @Command(
            name = "/country",
            arguments = "'название страны/стран'",
            description = """
                    Сортировка по странам. Список стран указывается через пробел
                    Вызов команды без указания страны(ан) сбрасывает фильтр по странам""")
    public String country(Object[] arguments) {
        try {
            CountryCommand.setCountry((String[]) arguments);
            return notifyAboutSuccessfulResult("/country");
        } catch (CommandException exception) {
            return notifyAboutUnsuccessfulResult("/country", exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            return notifyAboutUnsuccessfulResult("/country");
        }
    }

    private List<Command> getAllCommands() {
        return Arrays
                .stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Command.class))
                .map(m -> m.getAnnotation(Command.class))
                .collect(Collectors.toList());
    }

    private String notifyAboutSuccessfulResult(String commandName) {
        return String.format("Команда %s успешно выполнена", commandName);
    }

    private String notifyAboutUnsuccessfulResult(String commandName) {
        return String.format("Ошибка в процессе выполнения команды %s\nДля справки используйте /help", commandName);
    }

    private String notifyAboutUnsuccessfulResult(String commandName, String errorMessage) {
        return String.format(
                "Ошибка в процессе выполнения команды %s\n%s\nДля справки используйте /help",
                commandName,
                errorMessage);
    }
}
