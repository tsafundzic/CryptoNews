package com.example.cobe.cryptonews.interaction

import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 11/04/2018.
 */
interface ArticlesInteractorInterface {

    interface ResponseInterface {

        fun onArticlesSuccess(articles: List<Article>)

        fun onArticlesError()

    }

    fun getArticles(responseInterface: ResponseInterface)

    fun getSearchedArticles(responseInterface: ResponseInterface, text: String, date: String)

}