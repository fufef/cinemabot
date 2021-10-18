package database;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import kinopoiskAPI.Filter;

import java.math.BigDecimal;

// TODO добавить методы по типу isLastPageOpen
public class UserParameters {
    private JsonObject searchResult;
    private Filter filter;
    private int numberOfCurrentFilm;
    private int countOfFilmsOnCurrentPage;
    private int pagesCount;

    public UserParameters(JsonObject searchResult, Filter filter, int numberOfCurrentFilm) throws Exception {
        setSearchResult(searchResult);
        setFilter(filter);
        setPagesCount(getPageCount(searchResult));
        setCountOfFilmsOnCurrentPage(getCountOfFilmsOnCurrentPage(searchResult));
        setNumberOfCurrentFilm(numberOfCurrentFilm);
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public int getNumberOfCurrentFilm() {
        return numberOfCurrentFilm;
    }

    public void setNumberOfCurrentFilm(int numberOfCurrentFilm) throws Exception {
        if (numberOfCurrentFilm < 0 || numberOfCurrentFilm > countOfFilmsOnCurrentPage)
            throw new Exception(); //TODO описание ошибки
        this.numberOfCurrentFilm = numberOfCurrentFilm;
    }

    public int getCountOfFilmsOnCurrentPage() {
        return countOfFilmsOnCurrentPage;
    }

    public void setCountOfFilmsOnCurrentPage(int countOfFilmsOnCurrentPage) throws Exception {
        if (countOfFilmsOnCurrentPage < 0)
            throw new Exception(); //TODO описание ошибки
        this.countOfFilmsOnCurrentPage = countOfFilmsOnCurrentPage;
    }

    public int getNumberOfCurrentPage() {
        return filter.getPage();
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) throws Exception {
        if (pagesCount < 0)
            throw new Exception(); //TODO описание ошибки
        this.pagesCount = pagesCount;
    }

    public JsonObject getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(JsonObject searchResult) {
        this.searchResult = searchResult;
    }

    public JsonObject getCurrentFilm() {
        //TODO проверки!!
        JsonArray films  = (JsonArray) this.searchResult.get("films");
        return (JsonObject) (films).get(this.numberOfCurrentFilm);
    }

    private int getPageCount(JsonObject searchResult) {
        BigDecimal count = (BigDecimal) searchResult.get("pagesCount");
        if (count == null)
            return 0;
        return count.intValue();
    }

    private int getCountOfFilmsOnCurrentPage(JsonObject searchResult) {
        var films = searchResult.get("films");
        if (films == null)
            return 0;
        return ((JsonArray) films).size();
    }
}
