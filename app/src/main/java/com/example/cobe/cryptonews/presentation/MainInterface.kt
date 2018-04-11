package com.example.cobe.cryptonews.presentation

import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 11/04/2018.
 */
interface MainInterface {
    interface View {
        fun setSearchError()

        fun startSearchActivity(text: String)

        fun startSearchActivity(text: String, date: String)

        fun setDateError()

        fun setArticlesFailure()

        fun showArticles(articles: List<Article>)

        fun startArticleDetails(url: String)
    }

    interface Presenter : BasePresenter<View> {

        fun onArticleInputSearch(text: String)

        fun onArticleDateSearch(text: String, date: String)

        fun getArticles()

        fun articleDetails(url: String)
    }
}