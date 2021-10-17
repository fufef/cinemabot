package database;

import java.io.IOException;

public interface Database {
    void pushData(String userId, UserParameters userParameters) throws IOException;

    UserParameters pullData(String userId);
}
