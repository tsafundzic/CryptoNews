package com.example.cobe.cryptonews.model;

import java.util.List;

/**
 * Created by cobe on 29/03/2018.
 */

public class ArticlesResponse {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

}
