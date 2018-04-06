package com.example.cobe.cryptonews.ui.main;

import com.example.cobe.cryptonews.api.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.api.ResponseInterface;
import com.example.cobe.cryptonews.comm.ValidationUtils;
import com.example.cobe.cryptonews.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobe on 06/04/2018.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private ArticlesInteractorInterface articlesInteractor;
    private ResponseInterface responseInterface;

    MainPresenter(MainContract.View view, ArticlesInteractorInterface articlesInteractor, ResponseInterface responseInterface) {
        this.view = view;
        this.articlesInteractor = articlesInteractor;
        this.responseInterface = responseInterface;
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
        articlesInteractor.getArticles(responseInterface);
    }


    private Callback<ArticlesResponse> getArticlesCallback() {
        return new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                if (response.body() != null) {
                    view.showArticles(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {

            }
        };
    }

    @Override
    public void getDate() {

    }

}
