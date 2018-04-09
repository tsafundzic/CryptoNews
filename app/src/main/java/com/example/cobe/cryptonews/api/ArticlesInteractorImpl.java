package com.example.cobe.cryptonews.api;

import android.support.annotation.NonNull;

import com.example.cobe.cryptonews.constants.Constants;
import com.example.cobe.cryptonews.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobe on 06/04/2018.
 */
public class ArticlesInteractorImpl implements ArticlesInteractorInterface {

    private ApiInterface apiInterface;

    public ArticlesInteractorImpl(ApiInterface api) {
        this.apiInterface = api;
    }

    @Override
    public void getArticles(ResponseInterface responseInterface) {
        Call<ArticlesResponse> call = apiInterface.getArticles(Constants.SOURCES, Constants.API_KEY);
        callArticles(responseInterface, call);
    }

    @Override
    public void getSearchedArticles(ResponseInterface responseInterface, String text, String date) {
        Call<ArticlesResponse> call = apiInterface.getSearchedArticles(text, date, date, Constants.API_KEY);
        callArticles(responseInterface, call);
    }

    private void callArticles(final ResponseInterface responseInterface, Call<ArticlesResponse> call) {
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
                responseInterface.onArticlesSuccess(response.body().getArticles());
            }

            @Override
            public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
                responseInterface.onArticlesError();
            }
        });
    }

}