package com.example.cobe.cryptonews.articlesSearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobe.cryptonews.ArticleAdapter;
import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.model.ArticlesResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesBasedOnSearchActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener {

    private static final String PUBLISHED_AT = "publishedAt";
    private static final String API_KEY = "7bd2f92ac63845d8bbd831a30c423140";

    String searchWord;
    private final ArticleAdapter adapter = new ArticleAdapter();

    @BindView(R.id.rvArticlesSearchList)
    RecyclerView recyclerView;
    @BindView(R.id.tvSearchWord)
    TextView searchedWord;
    @BindView(R.id.back)
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_based_on_search);

        ButterKnife.bind(this);
        receiveSearchWord();
        setUI();
        apiCall();
        setAdapter();
    }

    private void setUI() {
        searchedWord.setText(searchWord);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnArticleClickListener(this);
    }

    @OnClick(R.id.back)
    public void returnBack() {
        onBackPressed();
    }

    public static Intent getLaunchIntent(Context from, String searchWord) {
        Intent intent = new Intent(from, ArticlesBasedOnSearchActivity.class);
        intent.putExtra("SEARCH", searchWord);
        return intent;
    }

    private void receiveSearchWord() {
        Intent intent = getIntent();
        searchWord = intent.getStringExtra("SEARCH");
    }

    private void apiCall() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesResponse> call = api.getArticlesBasedOnTypedWord(searchWord, PUBLISHED_AT, API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
        List<Article> articles = response.body().getArticles();
        adapter.setArticles(articles);
    }

    @Override
    public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {

    }

    @Override
    public void onArticleClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
