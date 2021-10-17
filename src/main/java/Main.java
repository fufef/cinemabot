import botLogic.Database;
import bots.consoleBot.ConsoleBot;
import database.jsonDatabase.JSONDatabase;

public class Main {
    public static void main(String[] args) {
        Database.database = new JSONDatabase();
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}