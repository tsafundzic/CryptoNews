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
import com.example.cobe.cryptonews.api.ArticlesInteractorImpl;
import com.example.cobe.cryptonews.api.ArticlesInteractorInterface;
import com.example.cobe.cryptonews.api.ResponseInterface;
import com.example.cobe.cryptonews.comm.DialogUtils;
import com.example.cobe.cryptonews.ui.articleSearch.ArticlesSearchActivity;
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter;
import com.example.cobe.cryptonews.constants.Constants;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnArticleClickListener, DatePickerDialog.OnDateSetListener, MainContract.View {

    @BindView(R.id.rvArticleList)
    RecyclerView recyclerView;

    @BindView(R.id.etInputWord)
    EditText searchWord;

    private final ArticleAdapter adapter = new ArticleAdapter();
    private String date;

    private MainContract.Presenter presenter;
    private ResponseInterface responseInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ArticlesInteractorInterface articlesInteractor = new ArticlesInteractorImpl(ApiClient.getApi());

        presenter = new MainPresenter(this, articlesInteractor, responseInterface);

        presenter.getArticles();
        setAdapter();
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnArticleClickListener(this);
    }

    @Override
    public void onArticleClick(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @OnClick(R.id.selectDate)
    public void showDialog() {
        DialogUtils.showDatePicker(this, this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        date = Constants.format.format(calendar.getTime());
        startFilter();
    }

    @Override
    public void showArticles(List<Article> articles) {
        adapter.setArticles(articles);
    }

    @Override
    public void setArticlesFailure() {
        Toast.makeText(this, R.string.error_cant_get_articles, Toast.LENGTH_SHORT).show();
    }

    public void startFilter() {
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
