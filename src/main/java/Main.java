import botLogic.userData.UsersData;
import bots.consoleBot.ConsoleBot;
import kinopoiskAPI.API;
import userParametersRepository.Repository;
import userParametersRepository.jsonUserParametersRepository.JSONUserParametersRepository;

public class Main {
    public static void main(String[] args) {
        var friends = API.getInformationAboutFilmById(77044);
        var avengers = API.getInformationAboutFilmById(263531);
        var blinders = API.getInformationAboutFilmById(716587);
        UsersData.initializeRepository(
                Repository.getInstance(new JSONUserParametersRepository(
                        "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json")));
        new ConsoleBot().start();
    }
}