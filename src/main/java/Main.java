import botLogic.userData.UsersData;
import com.google.gson.Gson;
import com.pengrad.telegrambot.TelegramBot;
import kinopoiskAPI.API;
import telegramBot.telegramBot;
import telegramBot.BotToken;
import userParametersRepository.Repository;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;

import java.io.*;
import java.net.URISyntaxException;

public class Main {
    public static String TOKEN = "";
    public static TelegramBot bot = new TelegramBot(TOKEN);

    public static void main(String[] args) throws URISyntaxException, IOException {
        var text = "";
        var friends = API.getInformationAboutFilmById(77044);
        var avengers = API.getInformationAboutFilmById(263531);
        var blinders = API.getInformationAboutFilmById(716587);
        UsersData.initializeRepository(
                Repository.getInstance(new JSONUserParametersRepository(
                        "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json")));

        var bot = new telegramBot(getToken().token);
    }

    private static BotToken getToken() throws IOException, URISyntaxException {
        Reader input = new FileReader("./src/main/java/telegramBot/token.json");
        var gson = new Gson();
        return gson.fromJson(input, BotToken.class);
    }
}