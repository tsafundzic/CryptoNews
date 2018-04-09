package com.example.cobe.cryptonews.ui.main;

import com.example.cobe.cryptonews.api.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.comm.ValidationUtils;
import com.example.cobe.cryptonews.model.Article;

import java.util.List;

/**
 * Created by cobe on 06/04/2018.
 */

public class MainPresenter implements MainContract.Presenter, ArticlesInteractorInterface.ResponseInterface {

    private MainContract.View view;
    private ArticlesInteractorInterface articlesInteractor;

    MainPresenter(MainContract.View view, ArticlesInteractorInterface articlesInteractor) {
        this.view = view;
        this.articlesInteractor = articlesInteractor;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onArticleInputSearch(String text) {
        if (ValidationUtils.isEmpty(text)) {
            view.setSearchError();
        } else {
            view.startSearchActivity(text);
        }
    }

    @Override
    public void onArticleDateSearch(String text, String date) {
        if (ValidationUtils.isEmpty(text)) {
            view.setSearchError();
        } else if (date.isEmpty()) {
            view.setDateError();
        } else {
            view.startSearchActivity(text, date);
        }
    }

    @Override
    public void getArticles() {
        articlesInteractor.getArticles(this);
    }

    @Override
    public void articleDetails(String url) {
        view.startArticleDetails(url);
    }

    @Override
    public void onArticlesSuccess(List<Article> articles) {
        view.showArticles(articles);
    }

    @Override
    public void onArticlesError() {
        view.setArticlesFailure();
    }
}
