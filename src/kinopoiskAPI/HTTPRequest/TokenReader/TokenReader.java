package kinopoiskAPI.HTTPRequest.TokenReader;

import java.io.*;

public class TokenReader {
    private static final String pathToToken;

    static {
        pathToToken = "src//kinopoiskAPI//HTTPRequest//TokenReader//token";
    }

    public static String ReadToken() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToToken))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("The token was not found");
            System.exit(1);
        }
        return null;
    }
}