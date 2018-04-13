package com.example.cobe.cryptonews.ui.articleSearch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.cobe.cryptonews.R
import com.example.cobe.cryptonews.api.ApiClient.Companion.getArticleInteractor
import com.example.cobe.cryptonews.common.extensions.getIntent
import com.example.cobe.cryptonews.common.helpers.toast
import com.example.cobe.cryptonews.listeners.OnArticleClickListener
import com.example.cobe.cryptonews.model.Article
import com.example.cobe.cryptonews.presentation.SearchInterface
import com.example.cobe.cryptonews.presentation.implementation.SearchPresenterImpl
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter
import kotlinx.android.synthetic.main.activity_articles_search.*

class ArticlesSearchActivity : AppCompatActivity(), SearchInterface.View, OnArticleClickListener {

    private val adapter by lazy { ArticleAdapter(this) }
    private val presenter: SearchInterface.Presenter by lazy { SearchPresenterImpl(getArticleInteractor()) }

    companion object {
        private const val KEY_SEARCH = "SEARCH"
        private const val KEY_DATE = "DATE"

        fun getLaunchIntent(from: Context, searchedWord: String, date: String) = from.getIntent<ArticlesSearchActivity>().apply {
            putExtra(KEY_SEARCH, searchedWord)
            putExtra(KEY_DATE, date)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_search)
        presenter.setView(this)

        receiveSearchDetails()
        setAdapter()

        back.setOnClickListener { goBack() }
    }

    private fun receiveSearchDetails() {
        val intent = intent
        presenter.setTitle(intent.getStringExtra(KEY_SEARCH), intent.getStringExtra(KEY_DATE))
    }

    private fun setAdapter() {
        articlesSearchList.layoutManager = LinearLayoutManager(this)
        articlesSearchList.adapter = adapter
    }

    override fun onArticleClick(url: String) = presenter.articleDetails(url)

    override fun showArticles(articles: List<Article>) = adapter.setArticles(articles)

    override fun articleFailure() = toast(getString(R.string.error_cant_get_articles))

    override fun startArticleDetails(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun showTitle(title: String, date: String) {
        searchWord.text = title
        selectedDate.text = date
        presenter.getSearchedArticles(title, date)
    }

    private fun goBack() = finish()
}
