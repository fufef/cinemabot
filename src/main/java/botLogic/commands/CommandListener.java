package botLogic.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandListener {
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
        try {
            return AdviseCommand.advise();
        } catch (Exception e) {
            return "Ошибка в процессе выполнения команды /advise";
        }
    }

    @Command(
            name = "/name",
            arguments = "'название фильма'",
            maxArgs = 1,
            description = """
                    Бот будет искать фильмы с похожим названием.
                    Вызов команды без указания имени фильма сбрасывает фильтр по имени"""
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
    }

    @Command(
            name = "/country",
            arguments = "'название страны/стран'",
            description = """
                    Сортировка по странам. Список стран указывается через пробел.
                    Вызов команды без указания страны(ан) сбрасывает фильтр по странам.""")
    public void country(Object[] arguments) {
    }

    private String[] pullOutArguments(Object[] arguments) {
        return (String[]) arguments;
    }

    private List<Command> getAllCommands() {
        return Arrays
                .stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Command.class))
                .map(m -> m.getAnnotation(Command.class))
                .collect(Collectors.toList());
    }
}
