package ru.t1;

import okhttp3.OkHttpClient;

public abstract class BaseApiService {
    public final OkHttpClient client = new OkHttpClient();

}
