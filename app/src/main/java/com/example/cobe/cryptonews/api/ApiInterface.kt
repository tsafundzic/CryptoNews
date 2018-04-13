package com.example.cobe.cryptonews.api

import com.example.cobe.cryptonews.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by cobe on 13/04/2018.
 */
interface ApiInterface {

    @GET("v2/everything")
    fun getArticles(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Call<ArticlesResponse>

    @GET("v2/everything")
    fun getSearchedArticles(@Query("q") typedWord: String, @Query("from") startDate: String, @Query("to") endDate: String, @Query("apiKey") apiKey: String): Call<ArticlesResponse>
}