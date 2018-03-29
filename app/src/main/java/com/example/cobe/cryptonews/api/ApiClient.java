package com.example.cobe.cryptonews.api;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by cobe on 29/03/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "https://www.investopedia.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
