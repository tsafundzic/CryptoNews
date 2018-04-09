package com.example.cobe.cryptonews.ui.articleSearch;

import com.example.cobe.cryptonews.model.Article;

import java.util.List;

/**
 * Created by cobe on 09/04/2018.
 */

public interface SearchContract {

    interface View {

        void showArticles(List<Article> articles);

        void articleFailure();

        void startArticleDetails(String url);

        void showTitle(String title, String date);

    }

    interface Presenter {

        void setView(View view);

        void getSearchedArticles(String title, String date);

        void articleDetails(String url);

        void setTitle(String title, String date);
    }
}
