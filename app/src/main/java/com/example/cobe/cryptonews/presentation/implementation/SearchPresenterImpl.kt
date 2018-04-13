package com.example.cobe.cryptonews.presentation.implementation

import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface
import com.example.cobe.cryptonews.model.Article
import com.example.cobe.cryptonews.presentation.SearchInterface

/**
 * Created by cobe on 11/04/2018.
 */
class SearchPresenterImpl(private var articlesInteractor: ArticlesInteractorInterface) : SearchInterface.Presenter, ArticlesInteractorInterface.ResponseInterface {

    private lateinit var view: SearchInterface.View

    override fun setView(view: SearchInterface.View) {
        this.view = view
    }

    override fun onArticlesSuccess(articles: List<Article>) {
        view.showArticles(articles)
    }

    override fun onArticlesError() {
        view.articleFailure()
    }

    override fun getSearchedArticles(title: String, date: String) {
        articlesInteractor.getSearchedArticles(this, title, date)
    }

    override fun articleDetails(url: String) {
        view.startArticleDetails(url)
    }

    override fun setTitle(title: String, date: String) {
        view.showTitle(title, date)
    }
}