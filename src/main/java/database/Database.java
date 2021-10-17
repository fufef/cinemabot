package database;

public interface Database {
    void uploadUserData(String userId, UserParameters userParameters);

    UserParameters downloadUserData(String userId);
}
