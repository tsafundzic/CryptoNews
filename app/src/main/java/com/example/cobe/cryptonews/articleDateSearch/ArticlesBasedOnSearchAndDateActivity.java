package com.example.cobe.cryptonews.articleDateSearch;

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

import com.example.cobe.cryptonews.ArticleAdapter;
import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.constants.ConstantsUtils;
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

public class ArticlesBasedOnSearchAndDateActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener {

    private final ArticleAdapter adapter = new ArticleAdapter();

    private String searchWord;
    private String date;

    @BindView(R.id.rvArticlesSearchList)
    RecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tvSearchWord)
    TextView searchedWord;
    @BindView(R.id.tvSelectedDate)
    TextView selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_based_on_search_and_date);

        ButterKnife.bind(this);
        receiveSearchWordAndDate();
        setUI();
        apiCall();
        setAdapter();
    }

    private void setUI() {
        searchedWord.setText(searchWord);
        selectedDate.setText(date);
    }

    public static Intent getLaunchIntent(Context from, String searchedWord, String date) {
        Intent intent = new Intent(from, ArticlesBasedOnSearchAndDateActivity.class);
        intent.putExtra("SEARCH", searchedWord);
        intent.putExtra("DATE", date);
        return intent;
    }

    public void receiveSearchWordAndDate() {
        Intent intent = getIntent();
        searchWord = intent.getStringExtra("SEARCH");
        date = intent.getStringExtra("DATE");
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnArticleClickListener(this);
    }

    @OnClick(R.id.back)
    public void goBack(){
        finish();
    }

    private void apiCall() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesResponse> call = api.getArticleBasedOnDateAndTypedWord(searchWord, date, date, ConstantsUtils.API_KEY);
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
