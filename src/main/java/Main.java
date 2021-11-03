import botLogic.UserParametersRepository;
import bots.consoleBot.ConsoleBot;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;

public class Main {
    public static void main(String[] args) {
        UserParametersRepository.userParametersRepository = new JSONUserParametersRepository(
                "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json");
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}