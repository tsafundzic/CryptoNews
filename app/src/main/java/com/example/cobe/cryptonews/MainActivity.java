package com.example.cobe.cryptonews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.model.RSSFeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<RSSFeed> {

    private static final String FEED_NAME = "rss_headline";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiCall();
    }

    private void apiCall() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<RSSFeed> call = api.getArticles(FEED_NAME);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
        List<Article> articles = response.body().getArticles();
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        Log.e("ERROR", t.toString());
    }
}
