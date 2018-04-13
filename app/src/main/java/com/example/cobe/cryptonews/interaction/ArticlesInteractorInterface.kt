package com.example.cobe.cryptonews.interaction

import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 11/04/2018.
 */
interface ArticlesInteractorInterface {

    interface ResponseInterface<in T> {

        fun onArticlesSuccess(data: T)

        fun onArticlesError()
    }

    fun getArticles(responseInterface: ResponseInterface<List<Article>>)

    fun getSearchedArticles(responseInterface: ResponseInterface<List<Article>>, text: String, date: String)

}