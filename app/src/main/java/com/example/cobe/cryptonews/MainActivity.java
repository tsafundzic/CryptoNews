package com.example.cobe.cryptonews;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobe.cryptonews.api.ApiClient;
import com.example.cobe.cryptonews.api.ApiInterface;
import com.example.cobe.cryptonews.articleDateSearch.ArticlesBasedOnSearchAndDateActivity;
import com.example.cobe.cryptonews.articlesSearch.ArticlesBasedOnSearchActivity;
import com.example.cobe.cryptonews.comm.ValidationUtils;
import com.example.cobe.cryptonews.constants.ConstantsUtils;
import com.example.cobe.cryptonews.dialog.DialogDatePicker;
import com.example.cobe.cryptonews.listeners.OnArticleClickListener;
import com.example.cobe.cryptonews.model.Article;
import com.example.cobe.cryptonews.model.ArticlesResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ArticlesResponse>, OnArticleClickListener, DatePickerDialog.OnDateSetListener {

    private final ArticleAdapter adapter = new ArticleAdapter();
    private String date;

    @BindView(R.id.rvArticleList)
    RecyclerView recyclerView;
    @BindView(R.id.showArticlesBasedOnInput)
    ImageView showArticlesBasedOnInput;
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

    private void apiCall() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ArticlesResponse> call = api.getArticles(ConstantsUtils.SOURCES, ConstantsUtils.API_KEY);
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

    @OnClick(R.id.selectDate)
    public void showDialog() {
        DialogFragment datePicker = new DialogDatePicker();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(calendar.getTime());
        startSearchBasedOnDateAndWord();
    }

    public void startSearchBasedOnDateAndWord() {
        if (ValidationUtils.isEmpty(searchWord.getText().toString())) {
            searchWord.setError(getText(R.string.wrong_input));
        } else if (date == null) {
            Toast.makeText(this, getText(R.string.wrong_date), Toast.LENGTH_SHORT).show();
        } else {
            startActivity(ArticlesBasedOnSearchAndDateActivity.getLaunchIntent(this, searchWord.getText().toString(), date));
        }
    }

    @OnClick(R.id.showArticlesBasedOnInput)
    public void startSearch() {
        if (ValidationUtils.isEmpty(searchWord.getText().toString())) {
            searchWord.setError(getText(R.string.wrong_input));
        } else {
            startActivity(ArticlesBasedOnSearchActivity.getLaunchIntent(this, searchWord.getText().toString()));
        }
    }

}
