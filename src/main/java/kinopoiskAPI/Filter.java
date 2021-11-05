package kinopoiskAPI;

public class Filter {
    private int[] countries;
    private int[] genres;
    private String type = "";
    private int ratingFrom;
    private int ratingTo;
    private int yearFrom;
    private int yearTo;
    private int page;

    public Filter() {
        countries = new int[0];
        genres = new int[0];
        page = 1;
        resetRatings();
        resetYears();
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
        return "";
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

    public void resetRatings() {
        ratingFrom = 0;
        ratingTo = 10;
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
