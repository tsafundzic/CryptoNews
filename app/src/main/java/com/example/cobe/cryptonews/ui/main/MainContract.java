package com.example.cobe.cryptonews.ui.main;


import com.example.cobe.cryptonews.model.Article;

import java.util.List;

/**
 * Created by cobe on 06/04/2018.
 */

public interface MainContract {

    interface View {

        void setSearchError();

        void startSearchActivity(String text);

        void startSearchActivity(String text, String date);

        void setDateError();

        void setArticlesFailure();

        void showArticles(List<Article> articles);

    }

    interface Presenter {

        void setView(View view);

        void onArticleInputSearch(String text);

        void onArticleDateSearch(String text, String date);

        void getArticles();

        void getDate();
    }
}
