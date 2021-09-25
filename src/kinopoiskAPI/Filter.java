package kinopoiskAPI;

public record Filter(
        int[] countries,
        int[] genres,
        String order,
        String type,
        int ratingFrom,
        int ratingTo,
        int yearFrom,
        int yearTo,
        int page) {
}
