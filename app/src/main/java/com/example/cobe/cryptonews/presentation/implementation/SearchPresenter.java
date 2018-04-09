package com.example.cobe.cryptonews.presentation.implementation;

import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.presentation.SearchContract;

import java.util.List;

/**
 * Created by cobe on 09/04/2018.
 */

public class SearchPresenter implements SearchContract.Presenter, ArticlesInteractorInterface.ResponseInterface {

    private SearchContract.View view;
    private ArticlesInteractorInterface articlesInteractor;

    public SearchPresenter(ArticlesInteractorInterface articlesInteractor) {
        this.articlesInteractor = articlesInteractor;
    }

    @Override
    public void setView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void getSearchedArticles(String title, String date) {
        articlesInteractor.getSearchedArticles(this, title, date);
    }

    @Override
    public void articleDetails(String url) {
        view.startArticleDetails(url);
    }

    @Override
    public void setTitle(String title, String date) {
        view.showTitle(title, date);
    }

    @Override
    public void onArticlesSuccess(List<Article> articles) {
        view.showArticles(articles);
    }

    @Override
    public void onArticlesError() {
        view.articleFailure();
    }
}
