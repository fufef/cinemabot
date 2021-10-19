import botLogic.Database;
import bots.consoleBot.ConsoleBot;
import database.jsonDatabase.JSONDatabase;
import kinopoiskAPI.API;

public class Main {
    public static void main(String[] args) {
        var uytrfdfghjh = API.getInformationAboutFilmById(2500772);
        Database.database = new JSONDatabase();
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}