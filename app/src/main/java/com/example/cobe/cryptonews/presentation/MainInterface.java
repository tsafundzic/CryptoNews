package com.example.cobe.cryptonews.presentation;


import com.example.cobe.cryptonews.model.Article;

import java.util.List;

/**
 * Created by cobe on 06/04/2018.
 */

public interface MainInterface {

    interface View {

        void setSearchError();

        void startSearchActivity(String text);

        void startSearchActivity(String text, String date);

        void setDateError();

        void setArticlesFailure();

        void showArticles(List<Article> articles);

        void startArticleDetails(String url);
    }

    interface Presenter extends BasePresenter<View> {

        void onArticleInputSearch(String text);

        void onArticleDateSearch(String text, String date);

        void getArticles();

        void articleDetails(String url);
    }
}
