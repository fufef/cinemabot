package botLogic;

public class UserId {
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserId.userId = userId;
    }
}
