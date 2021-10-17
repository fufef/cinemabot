import bots.consoleBot.ConsoleBot;
import database.JSONDatabase;

public class Main {
    public static void main(String[] args) {
        JSONDatabase d = new JSONDatabase();
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}