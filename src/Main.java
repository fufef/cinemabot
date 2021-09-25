import kinopoiskAPI.API;
import kinopoiskAPI.Filter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(API.getIdOfCountriesAndGenres());
        System.out.println(API.getInformationAboutFilmById(409424));
        Filter filter = new Filter(
                new int[]{1},
                new int[]{1},
                "RATING",
                "ALL",
                0,
                10,
                1888,
                2021,
                1);
        System.out.println(API.getInformationAboutFilmsByFilter(filter));
    }
}
