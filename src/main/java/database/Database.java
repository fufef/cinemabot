package database;

public interface Database { //todo  UserParametersRepository
    void uploadUserData(String userId, UserParameters userParameters); // todo save

    UserParameters downloadUserData(String userId); // todo get
}
