package kinopoiskAPI;

import java.util.ArrayList;
import java.util.HashSet;

public class Filter {
    private int[] countries;
    private int[] genres;
    private String order = "";
    private String type = "";
    private int ratingFrom;
    private int ratingTo;
    private int yearFrom;
    private int yearTo;
    private int page;

    public Filter() {
        countries = new int[0];
        genres = new int[0];
        ratingFrom = 0;
        ratingTo = 10;
        yearFrom = 0;
        yearTo = Integer.MAX_VALUE;
        page = 1;
    }

    public void addGenres(ArrayList genresId) {
        HashSet newGenresSet = new HashSet(genresId);
        for (int i = 0; i < genres.length; i++) {
            newGenresSet.add(genres[i]);
        }
        int[] newGenres = newGenresSet.stream().mapToInt(i -> (int) i).toArray();
        setGenres(newGenres);
    }

    public void addCountries(ArrayList countriesId) {
        HashSet newCountriesSet = new HashSet(countriesId);
        for (int i = 0; i < countries.length; i++) {
            newCountriesSet.add(countries[i]);
        }
        int[] newCountries = newCountriesSet.stream().mapToInt(i -> (int) i).toArray();
        setGenres(newCountries);
    }

    public int[] getCountries() {
        return countries;
    }

    public void setCountries(int[] countries) {
        this.countries = countries;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRatingTo() {
        return ratingTo;
    }

    public void setRatingTo(int ratingTo) {
        this.ratingTo = ratingTo;
    }

    public int getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(int ratingFrom) {
        this.ratingFrom = ratingFrom;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public void resetYears() {
        yearFrom = 0;
        yearTo = Integer.MAX_VALUE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
