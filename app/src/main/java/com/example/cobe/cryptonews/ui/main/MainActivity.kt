package com.example.cobe.cryptonews.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.DatePicker
import com.example.cobe.cryptonews.R
import com.example.cobe.cryptonews.common.helpers.getDate
import com.example.cobe.cryptonews.common.helpers.showDatePicker
import com.example.cobe.cryptonews.common.helpers.toast
import com.example.cobe.cryptonews.listeners.OnArticleClickListener
import com.example.cobe.cryptonews.mainPresenter
import com.example.cobe.cryptonews.model.Article
import com.example.cobe.cryptonews.presentation.MainInterface
import com.example.cobe.cryptonews.ui.articleSearch.ArticlesSearchActivity
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainInterface.View, DatePickerDialog.OnDateSetListener, OnArticleClickListener {

    private val adapter by lazy { ArticleAdapter(this) }
    private val presenter by lazy { mainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)

        setAdapter()
        presenter.getArticles()

        showArticlesBasedOnInput.setOnClickListener { startArticleSearch() }
        selectDate.setOnClickListener { showDialog() }
    }

    private fun setAdapter() {
        articleList.layoutManager = LinearLayoutManager(this)
        articleList.adapter = adapter
    }

    private fun showDialog() = showDatePicker(this, this)

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, date: Int) = startFilter(getDate(year, month, date))

    private fun startFilter(date: String) = presenter.onArticleDateSearch(inputWord.text.toString(), date)

    private fun startArticleSearch() = presenter.onArticleInputSearch(inputWord.text.toString())

    override fun onArticleClick(url: String) = presenter.articleDetails(url)

    override fun setSearchError() {
        inputWord.error = getString(R.string.wrong_input)
    }

    override fun startSearchActivity(text: String) = startActivity(ArticlesSearchActivity.getLaunchIntent(this, text, ""))

    override fun startSearchActivity(text: String, date: String) = startActivity(ArticlesSearchActivity.getLaunchIntent(this, text, date))

    override fun setDateError() = toast(getString(R.string.wrong_date))

    override fun setArticlesFailure() = toast(getString(R.string.error_cant_get_articles))

    override fun showArticles(articles: List<Article>) = adapter.setArticles(articles)

    override fun startArticleDetails(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
