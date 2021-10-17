import bots.consoleBot.ConsoleBot;
import database.jsonDatabase.JSONDatabase;
import database.UserParameters;
import kinopoiskAPI.Filter;

public class Main {
    public static void main(String[] args) throws Exception {
//        JSONDatabase d = new JSONDatabase();
//        UserParameters userParameters1 = new UserParameters(new Filter(), 0, 0, 0, 0);
//        d.uploadData("Alice", userParameters1);
//        UserParameters userParameters2 = new UserParameters(new Filter(), 100, 11212, 0, 0);
//        d.uploadData("Alice", userParameters2);
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}