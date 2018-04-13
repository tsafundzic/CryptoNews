package com.example.cobe.cryptonews.ui.articles

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.cobe.cryptonews.listeners.OnArticleClickListener
import com.example.cobe.cryptonews.model.Article
import kotlinx.android.synthetic.main.article_item.view.*

/**
 * Created by cobe on 13/04/2018.
 */
class ArticleViewHolder(itemView: View, private var onArticleClickListener: OnArticleClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var url: String

    fun setArticle(article: Article) {
        url = article.url

        Glide.with(itemView.articleImage.context).load(article.urlToImage).into(itemView.articleImage)
        itemView.articleTitle.text = article.title
        itemView.articleCreator.text = article.author
        itemView.articleDescription.text = article.description

        val publishedDate = article.publishedAt.split("T")
        itemView.articlePublishedAt.text = publishedDate[0]

        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        onArticleClickListener.onArticleClick(url)
    }
}