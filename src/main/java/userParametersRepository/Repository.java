package userParametersRepository;

public final class Repository implements UserParametersRepository {
    private static Repository instance;
    private final UserParametersRepository userParametersRepository;

    private Repository(UserParametersRepository userParametersRepository) {
        this.userParametersRepository = userParametersRepository;
    }

    public static Repository getInstance(UserParametersRepository userParametersRepository) {
        if (instance == null)
            instance = new Repository(userParametersRepository);
        return instance;
    }

    @Override
    public void saveUserData(String userId, UserParameters userData) {
        userParametersRepository.saveUserData(userId, userData);
    }

    @Override
    public UserParameters getUserData(String userId) {
        return userParametersRepository.getUserData(userId);
    }
}