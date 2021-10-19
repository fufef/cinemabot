import botLogic.UserParametersRepository;
import bots.consoleBot.ConsoleBot;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;
import kinopoiskAPI.API;

public class Main {
    public static void main(String[] args) {
        var uytrfdfghjh = API.getInformationAboutFilmById(2500772);
        UserParametersRepository.userParametersRepository = new JSONUserParametersRepository();
        ConsoleBot bot = new ConsoleBot();
        bot.start();
    }
}