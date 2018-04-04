package com.example.cobe.cryptonews.api;

import com.example.cobe.cryptonews.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cobe on 29/03/2018.
 */

public interface ApiInterface {
    @GET("v2/everything")
    Call<ArticlesResponse> getArticles(@Query("sources") String sources, @Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<ArticlesResponse> getArticlesBasedOnTypedWord(@Query("q") String typedWord, @Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<ArticlesResponse> getArticleBasedOnDateAndTypedWord(@Query("q") String typedWord, @Query("from") String startDate, @Query("to") String endDate, @Query("apiKey") String apiKey);
}
