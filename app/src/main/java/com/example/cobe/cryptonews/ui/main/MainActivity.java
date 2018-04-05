package com.example.cobe.cryptonews.ui.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cobe.cryptonews.R;
import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.comm.DialogUtils;
import com.example.cobe.cryptonews.ui.articleSearch.ArticlesSearchActivity;
import com.example.cobe.cryptonews.ui.articles.ArticleAdapter;
import com.example.cobe.cryptonews.comm.ValidationUtils;
import com.example.cobe.cryptonews.constants.Constants;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.model.ArticlesResponse;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.rvArticleList)
    RecyclerView recyclerView;

    @BindView(R.id.etInputWord)
    EditText searchWord;

    private final ArticleAdapter adapter = new ArticleAdapter();
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getArticles();
        setAdapter();
    }

    private void getArticles() {
        ApiInterface api = ApiClient.getApi();
        Call<ArticlesResponse> call = api.getArticles(Constants.SOURCES, Constants.API_KEY);
        call.enqueue(this);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnArticleClickListener(this);
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

    public void startFilter() {
        if (ValidationUtils.isEmpty(searchWord.getText().toString())) {
            searchWord.setError(getText(R.string.wrong_input));
        } else if (date == null) {
            Toast.makeText(this, getText(R.string.wrong_date), Toast.LENGTH_SHORT).show();
        } else {
            startActivity(ArticlesSearchActivity.getLaunchIntent(this, searchWord.getText().toString(), date));
        }
    }

    @OnClick(R.id.showArticlesBasedOnInput)
    public void startSearch() {
        if (ValidationUtils.isEmpty(searchWord.getText().toString())) {
            searchWord.setError(getText(R.string.wrong_input));
        } else {
            startActivity(ArticlesSearchActivity.getLaunchIntent(this, searchWord.getText().toString()));
        }
    }
}
