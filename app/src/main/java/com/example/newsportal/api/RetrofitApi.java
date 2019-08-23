package com.example.newsportal.api;

import com.example.newsportal.bean.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("top-headlines")
    Call<News>getNews(
        @Query("sources")
        String source ,
        @Query("apikey")
        String api
    );
    @GET("sources")
    Call<News> getSource(
            @Query("apikey")
            String api
    );
}
