package botLogic.userData;

public class UserId {
    private static String userId;

    public static String getIdOfCurrentUser() {
        return userId;
    }

    public static void setIdOfCurrentUser(String userId) {
        UserId.userId = userId;
    }
}
