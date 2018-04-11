package com.example.cobe.cryptonews.ui.articleSearch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.cobe.cryptonews.R
import com.example.cobe.cryptonews.api.ApiClient
import com.example.cobe.cryptonews.interaction.ArticlesInteractorImpl
import com.example.cobe.cryptonews.listeners.OnArticleClickListener
import com.example.cobe.cryptonews.model.Article
import com.example.cobe.cryptonews.presentation.SearchInterface
import com.example.cobe.cryptonews.presentation.implementation.SearchPresenterImpl
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter
import kotlinx.android.synthetic.main.activity_articles_search_kotlin.*

class ArticlesSearchActivityKotlin : AppCompatActivity(), SearchInterface.View, OnArticleClickListener {


    private val adapter = ArticleAdapter()
    private lateinit var presenter: SearchInterface.Presenter

    companion object {
        val KEY_SEARCH = "SEARCH"
        var KEY_DATE = "DATE"

        fun getLaunchIntent(from: Context, searchedWord: String, date: String): Intent {
            val intent = Intent(from, ArticlesSearchActivityKotlin::class.java)
            intent.putExtra(KEY_SEARCH, searchedWord)
            intent.putExtra(KEY_DATE, date)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_search_kotlin)

        injectDependencies()

        receiveSearchDetails()
        setAdapter()

        back.setOnClickListener { goBack() }
    }

    private fun receiveSearchDetails() {
        val intent = intent
        presenter.setTitle(intent.getStringExtra(KEY_SEARCH), intent.getStringExtra(KEY_DATE))
    }

    private fun injectDependencies() {
        val articlesInteractor = ArticlesInteractorImpl(ApiClient.getApi())
        presenter = SearchPresenterImpl(articlesInteractor)
        presenter.setView(this)
    }

    private fun setAdapter() {
        articlesSearchList.layoutManager = LinearLayoutManager(this)
        articlesSearchList.adapter = adapter
        adapter.setOnArticleClickListener(this)
    }

    override fun onArticleClick(url: String) {
        presenter.articleDetails(url)
    }

    override fun showArticles(articles: List<Article>) {
        adapter.setArticles(articles)
    }

    override fun articleFailure() {
        Toast.makeText(this, getString(R.string.error_cant_get_articles), Toast.LENGTH_SHORT).show()
    }

    override fun startArticleDetails(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun showTitle(title: String, date: String) {
        searchWord.text = title
        selectedDate.text = date
        presenter.getSearchedArticles(title, date)
    }

    private fun goBack() {
        finish()
    }

}
