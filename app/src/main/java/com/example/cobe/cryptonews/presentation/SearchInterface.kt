package com.example.cobe.cryptonews.presentation

import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 11/04/2018.
 */
interface SearchInterface {

    interface View {

        fun showArticles(articles: List<Article>)

        fun articleFailure()

        fun startArticleDetails(url: String)

        fun showTitle(title: String, date: String)

    }

    interface Presenter : BasePresenter<View> {

        fun getSearchedArticles(title: String, date: String)

        fun articleDetails(url: String)

        fun setTitle(title: String, date: String)

    }
}