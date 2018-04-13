package com.example.cobe.cryptonews.interaction

import com.example.cobe.cryptonews.api.ApiInterface
import com.example.cobe.cryptonews.common.constants.Constants
import com.example.cobe.cryptonews.model.ArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by cobe on 11/04/2018.
 */
class ArticlesInteractorImpl(private val apiInterface: ApiInterface) : ArticlesInteractorInterface {

    override fun getArticles(responseInterface: ArticlesInteractorInterface.ResponseInterface) {
        val call = apiInterface.getArticles(Constants.SOURCES, Constants.API_KEY)
        callArticles(responseInterface, call)
    }

    override fun getSearchedArticles(responseInterface: ArticlesInteractorInterface.ResponseInterface, text: String, date: String) {
        val call = apiInterface.getSearchedArticles(text, date, date, Constants.API_KEY)
        callArticles(responseInterface, call)
    }

    private fun callArticles(responseInterface: ArticlesInteractorInterface.ResponseInterface, call: Call<ArticlesResponse>?) {
        call?.enqueue(object : Callback<ArticlesResponse> {
            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                responseInterface.onArticlesSuccess(response.body()!!.articles)
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                responseInterface.onArticlesError()
            }
        })
    }
}