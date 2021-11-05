package botLogic.commands;

import botLogic.commands.adviseCommand.AdviseCommand;

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
        } catch (CommandException e) {
            return notifyAboutUnsuccessfulResult("/help", e.getMessage());
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
        } catch (CommandException e) {
            return notifyAboutUnsuccessfulResult("/advise", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return notifyAboutUnsuccessfulResult("/advise");
        }
    }

    @Command(
            name = "/genre",
            arguments = "'название жанра/жанров'",
            description = """
                    Сортировка по жанру/жанрам фильма (жанры указываются через пробел)
                    Вызов команды без указания жанра сбрасывает фильтр по жанрам"""
    )
    public void genre(Object[] arguments) {
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
            TypeCommand.type((String[]) arguments);
            return notifyAboutSuccessfulResult("/type");
        } catch (IllegalArgumentException e) {
            return notifyAboutUnsuccessfulResult("/type", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return notifyAboutUnsuccessfulResult("/type");
        }
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
                    Вызов команды без указания рейтинга сбрасывает фильтр по рейтингу""")
    public void rating(Object[] arguments) {
    }

    @Command(
            name = "/year",
            arguments = "y | y y | >y | <y",
            maxArgs = 2,
            description = """
                    Сортировка по году(ам) выхода фильма
                        /year y : поиск по указанному году
                        /year y-y : поиск по указанному временному промежутку
                        /year >y : поиск фильмов, выпущенных позже указанного года
                        /year <y : поиск фильмов, выпущенных ранее указанного года
                    Вызов команды без указания года(ов) сбрасывает фильтр по году""")
    public String year(Object[] arguments) {
        try {
            YearCommand.year((String[]) arguments);
            return notifyAboutSuccessfulResult("/year");
        } catch (CommandException e) {
            return notifyAboutUnsuccessfulResult("/year", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return notifyAboutUnsuccessfulResult("/year");
        }
    }

    @Command(
            name = "/country",
            arguments = "'название страны/стран'",
            description = """
                    Сортировка по странам. Список стран указывается через пробел
                    Вызов команды без указания страны(ан) сбрасывает фильтр по странам""")
    public void country(Object[] arguments) {
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
