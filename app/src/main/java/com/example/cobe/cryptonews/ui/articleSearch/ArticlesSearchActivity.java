package com.example.cobe.cryptonews.ui.articleSearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobe.cryptonews.interaction.ArticlesInteractorImpl;
import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.presentation.SearchContract;
import com.example.cobe.cryptonews.presentation.implementation.SearchPresenter;
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter;
import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticlesSearchActivity extends AppCompatActivity implements OnArticleClickListener, SearchContract.View {

    @BindView(R.id.rvArticlesSearchList)
    RecyclerView recyclerView;

    @BindView(R.id.tvSearchWord)
    TextView searchedWord;

    @BindView(R.id.tvSelectedDate)
    TextView selectedDate;

    private final ArticleAdapter adapter = new ArticleAdapter();

    private SearchContract.Presenter presenter;

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
        injectDependencies();

        receiveSearchDetails();
        setAdapter();
    }

    private void injectDependencies() {
        ArticlesInteractorInterface articlesInteractor = new ArticlesInteractorImpl(ApiClient.getApi());
        presenter = new SearchPresenter(articlesInteractor);
        presenter.setView(this);
    }

    public void receiveSearchDetails() {
        Intent intent = getIntent();
        presenter.setTitle(intent.getStringExtra(KEY_SEARCH), intent.getStringExtra(KEY_DATE));
    }

    @Override
    public void showTitle(String title, String date) {
        searchedWord.setText(title);
        selectedDate.setText(date);
        presenter.getSearchedArticles(title, date);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnArticleClickListener(this);
    }

    @Override
    public void onArticleClick(String url) {
        presenter.articleDetails(url);
    }

    @Override
    public void showArticles(List<Article> articles) {
        adapter.setArticles(articles);
    }

    @Override
    public void articleFailure() {
        Toast.makeText(this, R.string.error_cant_get_articles, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startArticleDetails(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.back)
    public void goBack() {
        finish();
    }
}
