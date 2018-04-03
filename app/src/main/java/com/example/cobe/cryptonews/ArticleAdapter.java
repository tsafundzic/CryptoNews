package com.example.cobe.cryptonews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobe on 30/03/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> articles = new ArrayList<>();

    private OnArticleClickListener onArticleClickListener;

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view, onArticleClickListener);
    }

    public void setOnArticleClickListener(OnArticleClickListener onArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        if (article != null) {
            holder.setArticle(article);
        }
    }

    public void setArticles(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
