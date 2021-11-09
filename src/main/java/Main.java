import botLogic.userData.UsersData;
import bots.consoleBot.ConsoleBot;
import kinopoiskAPI.API;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import telegram.TelegramAPI;
import userParametersRepository.Repository;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;
import org.telegram.telegrambots.ApiContextInitializer;

public class Main {
    public static void main(String[] args) {
        UsersData.initializeRepository(
                Repository.getInstance(new JSONUserParametersRepository(
                        "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json")));
        new ConsoleBot().start();
        ApiContextInitializer.init();
        DefaultBotOptions botOptions = .getInstance(DefaultBotOptions.class);
        TelegramAPI tgbot = new TelegramAPI(botOptions);
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try{
            botsApi.registerBot(tgbot);
        }
    }
}