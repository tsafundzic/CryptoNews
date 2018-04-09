package com.example.cobe.cryptonews.presentation.implementation;

import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.common.utils.ValidationUtils;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.presentation.MainInterface;

import java.util.List;

/**
 * Created by cobe on 06/04/2018.
 */

public class MainPresenterImpl implements MainInterface.Presenter, ArticlesInteractorInterface.ResponseInterface {

    private MainInterface.View view;
    private final ArticlesInteractorInterface articlesInteractor;

    public MainPresenterImpl(ArticlesInteractorInterface articlesInteractor) {
        this.articlesInteractor = articlesInteractor;
    }

    @Override
    public void setView(MainInterface.View view) {
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
