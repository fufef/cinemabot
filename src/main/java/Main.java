import botLogic.userData.UsersData;
import userParametersRepository.Repository;
import bots.consoleBot.ConsoleBot;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;

public class Main {
    public static void main(String[] args) {
        UsersData.userParametersRepository = Repository.getInstance(
                new JSONUserParametersRepository(
                        "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json"));
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}