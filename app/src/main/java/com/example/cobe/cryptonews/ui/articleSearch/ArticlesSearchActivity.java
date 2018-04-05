package com.example.cobe.cryptonews.ui.articleSearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobe.cryptonews.ui.articles.ArticleAdapter;
import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.constants.Constants;
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

public class ArticlesSearchActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener {

    @BindView(R.id.rvArticlesSearchList)
    RecyclerView recyclerView;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tvSearchWord)
    TextView searchedWord;

    @BindView(R.id.tvSelectedDate)
    TextView selectedDate;

    private final ArticleAdapter adapter = new ArticleAdapter();

    private String searchWord;
    private String date;

    private static final String KEY_SEARCH = "SEARCH";
    private static final String KEY_DATE = "DATE";

    public static Intent getLaunchIntent(Context from, String searchedWord, String date) {
        Intent intent = new Intent(from, ArticlesSearchActivity.class);
        intent.putExtra(KEY_SEARCH, searchedWord);
        intent.putExtra(KEY_DATE, date);
        return intent;
    }

    public static Intent getLaunchIntent(Context from, String searchWord) {
        Intent intent = new Intent(from, ArticlesSearchActivity.class);
        intent.putExtra(KEY_SEARCH, searchWord);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_based_on_search_and_date);

        ButterKnife.bind(this);
        receiveSearchWordAndDate();
        setUI();
        getArticles();
        setAdapter();
    }

    public void receiveSearchWordAndDate() {
        Intent intent = getIntent();
        searchWord = intent.getStringExtra(KEY_SEARCH);
        date = intent.getStringExtra(KEY_DATE);
    }

    private void setUI() {
        searchedWord.setText(searchWord);
        selectedDate.setText(date);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnArticleClickListener(this);
    }

    private void getArticles() {
        ApiInterface api = ApiClient.getApi();
        Call<ArticlesResponse> call = api.getArticleBasedOnDateAndTypedWord(searchWord, date, date, Constants.API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
        if (response.body() != null) {
            List<Article> articles = response.body().getArticles();
            adapter.setArticles(articles);
        }
    }

    @Override
    public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
        Toast.makeText(this, R.string.error_cant_get_articles, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onArticleClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.back)
    public void goBack() {
        finish();
    }
}
