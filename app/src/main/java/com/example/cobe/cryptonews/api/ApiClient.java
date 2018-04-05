package com.example.cobe.cryptonews.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cobe on 29/03/2018.
 */

public class ApiClient {

    private static final String BASE_URL = "https://newsapi.org/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApi() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
