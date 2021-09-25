package kinopoiskAPI.HTTPRequest;

import kinopoiskAPI.HTTPRequest.TokenReader.TokenReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    private static final Map<String, String> headers;

    static {
        headers = new HashMap<>();
        headers.put("X-API-KEY", TokenReader.ReadToken());
    }

    public static String request(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        for (var header :
                headers.entrySet())
            connection.setRequestProperty(header.getKey(), header.getValue());
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return input.readLine();
        } finally {
            connection.disconnect();
        }
    }
}
