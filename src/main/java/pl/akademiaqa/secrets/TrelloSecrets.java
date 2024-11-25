package pl.akademiaqa.secrets;

public class TrelloSecrets {

    private TrelloSecrets() {
    }

    private static final String KEY = "6c65c6c5183d940ecd51f58aa63dd587";
    private static final String TOKEN = "ATTA1abc50cab5b47d080b75d86580aa198db7d191e00ca864af1347eb79469b5a4a176CFA53";

    public static String getKey() {
        return KEY;
    }

    public static String getToken() {
        return TOKEN;
    }

}
