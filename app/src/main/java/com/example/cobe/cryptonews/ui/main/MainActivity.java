package com.example.cobe.cryptonews.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.interaction.ArticlesInteractorImpl;
import com.example.cobe.cryptonews.interaction.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.common.utils.DialogUtils;
import com.example.cobe.cryptonews.presentation.MainInterface;
import com.example.cobe.cryptonews.presentation.implementation.MainPresenterImpl;
import com.example.cobe.cryptonews.ui.articleSearch.ArticlesSearchActivity;
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnArticleClickListener, DatePickerDialog.OnDateSetListener, MainInterface.View {

    @BindView(R.id.rvArticleList)
    RecyclerView recyclerView;

    @BindView(R.id.etInputWord)
    EditText searchWord;

    private final ArticleAdapter adapter = new ArticleAdapter();

    private MainInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectDependencies();
        setAdapter();

        presenter.getArticles();
    }

    private void injectDependencies() {
        ArticlesInteractorInterface articlesInteractor = new ArticlesInteractorImpl(ApiClient.getApi());
        presenter = new MainPresenterImpl(articlesInteractor);
        presenter.setView(this);
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

    @OnClick(R.id.selectDate)
    public void showDialog() {
        DialogUtils.showDatePicker(this, this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        startFilter(DialogUtils.getDate(year, month, day));
    }

    @Override
    public void showArticles(List<Article> articles) {
        adapter.setArticles(articles);
    }

    @Override
    public void startArticleDetails(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void setArticlesFailure() {
        Toast.makeText(this, R.string.error_cant_get_articles, Toast.LENGTH_SHORT).show();
    }

    public void startFilter(String date) {
        presenter.onArticleDateSearch(searchWord.getText().toString(), date);
    }

    @Override
    public void setDateError() {
        Toast.makeText(this, getString(R.string.wrong_date), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSearchActivity(String text, String date) {
        startActivity(ArticlesSearchActivity.getLaunchIntent(this, text, date));
    }

    @OnClick(R.id.showArticlesBasedOnInput)
    public void startSearch() {
        presenter.onArticleInputSearch(searchWord.getText().toString());
    }

    @Override
    public void setSearchError() {
        searchWord.setError(getString(R.string.wrong_input));
    }

    @Override
    public void startSearchActivity(String text) {
        startActivity(ArticlesSearchActivity.getLaunchIntent(this, text));
    }
}
