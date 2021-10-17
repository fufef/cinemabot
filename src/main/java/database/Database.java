package database;

import java.io.IOException;

public interface Database {
    void uploadUserData(String userId, UserParameters userParameters) throws IOException;

    UserParameters downloadUserData(String userId);
}
