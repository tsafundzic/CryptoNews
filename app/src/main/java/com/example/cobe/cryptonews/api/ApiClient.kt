package com.example.cobe.cryptonews.api

import com.example.cobe.cryptonews.interaction.ArticlesInteractorImpl
import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by cobe on 13/04/2018.
 */
class ApiClient {

    companion object {

        private val BASE_URL: String = "https://newsapi.org/"
        private lateinit var retrofit: Retrofit

        private fun getApi(): ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        private fun getClient(): Retrofit {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit
        }

        fun getArticleInteractor(): ArticlesInteractorInterface = ArticlesInteractorImpl(getApi())
    }
}