package com.example.cobe.cryptonews.interaction

import com.example.cobe.cryptonews.api.ApiInterface
import com.example.cobe.cryptonews.common.constants.API_KEY
import com.example.cobe.cryptonews.common.constants.SOURCES
import com.example.cobe.cryptonews.common.extensions.processRequest
import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 11/04/2018.
 */
class ArticlesInteractorImpl(private val apiInterface: ApiInterface) : ArticlesInteractorInterface {

    override fun getArticles(responseInterface: ArticlesInteractorInterface.ResponseInterface<List<Article>>) {
        apiInterface.getArticles(SOURCES, API_KEY).processRequest(responseInterface)
    }

    override fun getSearchedArticles(responseInterface: ArticlesInteractorInterface.ResponseInterface<List<Article>>, text: String, date: String) {
        apiInterface.getSearchedArticles(text, date, date, API_KEY).processRequest(responseInterface)
    }
}