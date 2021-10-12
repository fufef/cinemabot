import bots.consoleBot.ConsoleBot;
import database.JSONDatabase;
import kinopoiskAPI.Filter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONDatabase d = new JSONDatabase();
        d.pushData("feg", new Filter());
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}