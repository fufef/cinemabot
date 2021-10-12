package database;

import kinopoiskAPI.Filter;

import java.io.IOException;

public interface Database {
    void pushData(String userId, Filter filter) throws IOException;

    Filter pullData(String userId);
}
