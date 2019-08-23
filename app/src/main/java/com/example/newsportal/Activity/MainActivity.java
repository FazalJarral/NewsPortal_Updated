package com.example.newsportal.Activity;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.newsportal.Adapter.NewsViewAdapter;
import com.example.newsportal.api.ApiClient;
import com.example.newsportal.api.RetrofitApi;
import com.example.newsportal.bean.Article;
import com.example.newsportal.bean.News;
import com.example.newsportal.bean.Soruce;
import com.example.newsportal.R;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextToSpeech mtts;
    RecyclerView recyclerView;
    NewsViewAdapter adapter;
    int noOfColumns;
    Soruce source;
    ArrayList<Article> data = new ArrayList<>();
    public final String API_KEY = "7457f8ceb29c4d79acdc9e14c26a3a13";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(getIntent()!=null){
            source = (Soruce) getIntent().getSerializableExtra("source");
        }
        mtts = new TextToSpeech(getApplicationContext() ,new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    mtts.setLanguage(Locale.ENGLISH);
                    mtts.speak("Hello" , TextToSpeech.QUEUE_FLUSH , null);
                }
                if(status == TextToSpeech.ERROR){
                    Toast.makeText(MainActivity.this, "Initilaztion Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    LoadJSON();
    }

    private void LoadJSON() {
        RetrofitApi client = ApiClient.getApiClient().create(RetrofitApi.class);
        Call<News> call;
        call = client.getNews(source.getId() , API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    if (!data.isEmpty()){
                        data.clear();
                    }
                    data = response.body().getArticles();
                    adapter = new NewsViewAdapter(getApplicationContext() , data);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
