package com.example.cobe.cryptonews.presentation.implementation

import com.example.cobe.cryptonews.common.utils.ValidationUtils
import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface
import com.example.cobe.cryptonews.model.Article
import com.example.cobe.cryptonews.presentation.MainInterface

/**
 * Created by cobe on 11/04/2018.
 */
class MainPresenterImpl(private val articlesInteractor: ArticlesInteractorInterface) : MainInterface.Presenter, ArticlesInteractorInterface.ResponseInterface {

    private lateinit var view: MainInterface.View

    override fun setView(view: MainInterface.View) {
        this.view = view
    }

    override fun onArticleInputSearch(text: String) {
        if (ValidationUtils.isEmpty(text)) {
            view.setSearchError()
        } else {
            view.startSearchActivity(text)
        }
    }

    override fun onArticleDateSearch(text: String, date: String) {
        when {
            ValidationUtils.isEmpty(text) -> view.setSearchError()
            ValidationUtils.isEmpty(date) -> view.setDateError()
            else -> view.startSearchActivity(text, date)
        }
    }

    override fun getArticles() {
        articlesInteractor.getArticles(this)
    }

    override fun articleDetails(url: String) {
        view.startArticleDetails(url)
    }

    override fun onArticlesSuccess(articles: List<Article>) {
        view.showArticles(articles)
    }

    override fun onArticlesError() {
        view.setArticlesFailure()
    }
}