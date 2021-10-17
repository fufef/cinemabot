import bots.consoleBot.ConsoleBot;
import database.JSONDatabase;
import database.UserParameters;
import kinopoiskAPI.Filter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        JSONDatabase d = new JSONDatabase();
//        d.pushData(
//                "Bob",
//                new UserParameters(new Filter(),
//                        0,
//                        0,
//                        0,
//                        0));
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}