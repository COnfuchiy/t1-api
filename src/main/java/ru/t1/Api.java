package ru.t1;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Api {
    private static final OkHttpClient client = new OkHttpClient();

    public static String sendGetRequest(String urlStr) throws IOException {
        Request request = new Request.Builder()
                .url(urlStr)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}
