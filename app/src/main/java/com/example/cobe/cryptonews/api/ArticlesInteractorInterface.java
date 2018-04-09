package com.example.cobe.cryptonews.api;


import com.example.cobe.cryptonews.model.Article;

import java.util.List;

/**
 * Created by cobe on 06/04/2018.
 */

public interface ArticlesInteractorInterface {

    interface ResponseInterface {

        void onArticlesSuccess(List<Article> articles);

        void onArticlesError();
    }

    void getArticles(ResponseInterface responseInterface);

    void getSearchedArticles(ResponseInterface responseInterface, String text, String date);

}
