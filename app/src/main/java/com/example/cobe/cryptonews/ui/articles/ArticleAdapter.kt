package com.example.cobe.cryptonews.ui.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cobe.cryptonews.R
import com.example.cobe.cryptonews.listeners.OnArticleClickListener
import com.example.cobe.cryptonews.model.Article

/**
 * Created by cobe on 13/04/2018.
 */
class ArticleAdapter(private val onArticleClickListener: OnArticleClickListener) : RecyclerView.Adapter<ArticleViewHolder>() {

    //private val articles = mutableListOf()<Article>()
    private var articles: MutableList<Article> = ArrayList()

    fun setArticles(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.setArticle(article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view, onArticleClickListener)
    }

    override fun getItemCount() = articles.size
}