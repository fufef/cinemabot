package database;

import kinopoiskAPI.Filter;

public class UserParameters {
    private Filter filter;
    private int numberOfCurrentFilm;
    private int countOfFilmsOnCurrentPage;
    private int numberOfCurrentPage;
    private int pagesCount;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public int getNumberOfCurrentFilm() {
        return numberOfCurrentFilm;
    }

    public void setNumberOfCurrentFilm(int numberOfCurrentFilm) {
        this.numberOfCurrentFilm = numberOfCurrentFilm;
    }

    public int getCountOfFilmsOnCurrentPage() {
        return countOfFilmsOnCurrentPage;
    }

    public void setCountOfFilmsOnCurrentPage(int countOfFilmsOnCurrentPage) {
        this.countOfFilmsOnCurrentPage = countOfFilmsOnCurrentPage;
    }

    public int getNumberOfCurrentPage() {
        return numberOfCurrentPage;
    }

    public void setNumberOfCurrentPage(int numberOfCurrentPage) {
        this.numberOfCurrentPage = numberOfCurrentPage;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public UserParameters(
            Filter filter,
            int numberOfCurrentFilm,
            int countOfFilmsOnCurrentPage,
            int numberOfCurrentPage,
            int pagesCount) {
        this.filter = filter;
        this.numberOfCurrentFilm = numberOfCurrentFilm;
        this.countOfFilmsOnCurrentPage = countOfFilmsOnCurrentPage;
        this.numberOfCurrentPage = numberOfCurrentPage;
        this.pagesCount = pagesCount;
    }
}
