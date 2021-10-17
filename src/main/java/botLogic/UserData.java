package botLogic;

public class UserData {
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserData.userId = userId;
    }
}
