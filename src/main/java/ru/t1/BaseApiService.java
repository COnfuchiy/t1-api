package ru.t1;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ru.t1.json.JsonEncodable;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class BaseApiService {
    private final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build();

    private String sendRequest(String url,Map<String, String> queryParams, boolean isPost, okhttp3.RequestBody requestBody) throws IOException {

        var urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        if (queryParams != null) {
            for (Map.Entry<String, String> param : queryParams.entrySet()) {
                urlBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        var urlObject = urlBuilder.build();
        var requestBuilder = new Request.Builder().url(urlObject);

        System.out.print(urlObject);

        if (isPost){
            requestBuilder = requestBuilder.post(Objects.requireNonNullElseGet(requestBody, () -> RequestBody.create("", MediaType.parse("application/json"))));
        }

        var request = requestBuilder.build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            if (response.body() == null) {
                throw new IOException("Response body is null");
            }
            return response.body().string();
        }
    }

    public String sendPostRequest(String url, Map<String, String> queryParams,  String jsonBody) throws IOException {
        System.out.print("POST:");
        return sendRequest(url, queryParams, true, jsonBody == null ? null : RequestBody.create(jsonBody, MediaType.parse("application/json")));
    }
    public String sendGetRequest(String url, Map<String, String> queryParams) throws IOException {
        System.out.print("GET:");
        return sendRequest(url, queryParams, false, null);
    }

}
