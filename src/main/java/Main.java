import botLogic.userData.UsersData;
import bots.consoleBot.ConsoleBot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import kinopoiskAPI.API;
import userParametersRepository.Repository;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;

public class Main {
    public static String TOKEN = "";
    public static TelegramBot bot = new TelegramBot(TOKEN);

    public static void main(String[] args) {
        var text = "";
        var friends = API.getInformationAboutFilmById(77044);
        var avengers = API.getInformationAboutFilmById(263531);
        var blinders = API.getInformationAboutFilmById(716587);
        UsersData.initializeRepository(
                Repository.getInstance(new JSONUserParametersRepository(
                        "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json")));
        var consoleBot = new ConsoleBot();

        bot.setUpdatesListener(updates -> {
            if(updates.get(0).message() != null)
            {
                var message = updates.get(0).message().text();
                var chatId = updates.get(0).message().chat().id();
                bot.execute(new SendMessage(chatId, consoleBot.getAnswerOnMessage(message, chatId.toString())));
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

}