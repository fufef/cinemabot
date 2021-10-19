package userParametersRepository;

public interface UserParametersRepository {
    void save(String userId, UserParameters userParameters);

    UserParameters get(String userId);
}
