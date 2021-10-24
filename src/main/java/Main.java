import botLogic.UserParametersRepository;
import bots.consoleBot.ConsoleBot;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;
import kinopoiskAPI.API;

import static kinopoiskAPI.API.getIdOfCountriesAndGenres;

public class Main {
    public static void main(String[] args) {
        UserParametersRepository.userParametersRepository = new JSONUserParametersRepository();
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}