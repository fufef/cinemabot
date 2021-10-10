package botLogic;

import kinopoiskAPI.Filter;

public class UserParameters {
    private Filter filter;
    private int numberOfCurrentFilm;
    private int countOfFilmsOnCurrentPage;
    private int numberOfCurrentPage;
    private int pagesCount;

    public UserParameters(Filter filter, int numberOfCurrentFilm, int countOfFilmsOnCurrentPage, int numberOfCurrentPage, int pagesCount) {
        this.filter = filter;
        this.numberOfCurrentFilm = numberOfCurrentFilm;
        this.countOfFilmsOnCurrentPage = countOfFilmsOnCurrentPage;
        this.numberOfCurrentPage = numberOfCurrentPage;
        this.pagesCount = pagesCount;
    }
}
