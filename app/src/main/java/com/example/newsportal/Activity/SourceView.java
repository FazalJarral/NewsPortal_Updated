package com.example.newsportal.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.newsportal.Adapter.PageViewAdapter;
import com.example.newsportal.Activity.MainActivity;
import com.example.newsportal.Activity.Utility;
import com.example.newsportal.R;
import com.example.newsportal.api.ApiClient;
import com.example.newsportal.api.RetrofitApi;
import com.example.newsportal.bean.News;
import com.example.newsportal.bean.Soruce;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceView extends AppCompatActivity {
    ViewPager viewPager;
    PageViewAdapter adapter;
    ArrayList<Soruce> data = new ArrayList<>();
    public final String API_KEY = "7457f8ceb29c4d79acdc9e14c26a3a13";
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_view);
        viewPager = findViewById(R.id.viewpager);
        LoadSource();
    }

    private void LoadSource() {
        RetrofitApi client = ApiClient.getApiClient().create(RetrofitApi.class);
        Call<News> call;
        call = client.getSource(API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() ) {
                    if (!data.isEmpty()) {
                        data.clear();
                    }
                   drawable = Utility.getRandomDrawbleColor();
                    data = response.body().getSoruces();
                    adapter = new PageViewAdapter(data, getApplicationContext() , drawable);
                    viewPager.setAdapter(adapter);
                    viewPager.setPadding(130 , 0 ,130 , 0);
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {
                            drawable = Utility.getRandomDrawbleColor();
                          //  viewPager.setBackground(drawable);
                            adapter.notifyDataSetChanged();
                            }

                        @Override
                        public void onPageSelected(int i) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });
                }
                else Log.e("error",response.isSuccessful()+"");
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
