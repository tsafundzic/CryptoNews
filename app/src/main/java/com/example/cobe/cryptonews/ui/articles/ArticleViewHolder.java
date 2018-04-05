package com.example.cobe.cryptonews.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cobe on 30/03/2018.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.ivArticleImage)
    ImageView articleImage;

    @BindView(R.id.tvArticleTitle)
    TextView articleTitle;

    @BindView(R.id.tvArticleDescription)
    TextView articleDescription;

    @BindView(R.id.tvArticleCreator)
    TextView articleCreator;

    @BindView(R.id.tvArticlePublishedAt)
    TextView articlePublishedAt;

    private OnArticleClickListener onArticleClickListener;
    private String url;

    public ArticleViewHolder(View itemView, OnArticleClickListener onArticleClickListener) {
        super(itemView);

        this.onArticleClickListener = onArticleClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void setArticle(Article article) {
        url = article.getUrl();

        Glide.with(articleImage.getContext()).load(article.getUrlToImage()).into(articleImage);
        articleTitle.setText(article.getTitle());
        articleCreator.setText(article.getAuthor());
        articleDescription.setText(article.getDescription());

        String[] publishedDate = article.getPublishedAt().split("T");
        articlePublishedAt.setText(publishedDate[0]);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onArticleClickListener != null) {
            onArticleClickListener.onArticleClick(url);
        }
    }
}
