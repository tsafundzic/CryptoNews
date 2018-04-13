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
class ArticleViewHolder(itemView: View, private inline val onArticleClickListener: OnArticleClickListener) : RecyclerView.ViewHolder(itemView) {

    fun setArticle(article: Article) = with(itemView) {
        Glide.with(context).load(article.urlToImage).into(articleImage)
        articleTitle.text = article.title
        articleCreator.text = article.author
        articleDescription.text = article.description

        val publishedDate = article.publishedAt.split("T")
        articlePublishedAt.text = publishedDate[0]

        setOnClickListener { onArticleClickListener(article.url) }
    }
}