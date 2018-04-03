package com.example.cobe.cryptonews;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.articlesSearch.ArticlesBasedOnSearchActivity;
import com.example.cobe.cryptonews.comm.ValidationUtils;
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

public class MainActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener {

    private static final String SOURCES = "crypto-coins-news";
    private static final String API_KEY = "7bd2f92ac63845d8bbd831a30c423140";

    private final ArticleAdapter adapter = new ArticleAdapter();

    @BindView(R.id.rvArticleList)
    RecyclerView recyclerView;
    @BindView(R.id.showArticlesBasedOnInput)
    Button showArticlesBasedOnInput;
    @BindView(R.id.etInputWord)
    EditText searchWord;
    @BindView(R.id.selectDate)
    ImageView selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        apiCall();
        setAdapter();
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnArticleClickListener(this);
    }

    @OnClick(R.id.showArticlesBasedOnInput)
    public void startSearch() {
        if (ValidationUtils.isEmpty(searchWord.getText().toString())) {
            searchWord.setError(getText(R.string.wrong_input));
        } else {
            startActivity(ArticlesBasedOnSearchActivity.getLaunchIntent(this, searchWord.getText().toString()));
        }
    }

    private void apiCall() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesResponse> call = api.getArticles(SOURCES, API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
        List<Article> articles = response.body().getArticles();
        adapter.setArticles(articles);
    }

    @Override
    public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
        Toast.makeText(this, R.string.error_cant_get_articles, Toast.LENGTH_SHORT).show();
        Log.e("Error", t.toString());
    }

    @Override
    public void onArticleClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
