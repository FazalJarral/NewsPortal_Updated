package com.example.newsportal.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class News {
    @SerializedName("status")
            @Expose
    String status;
    @SerializedName("totalResult")
            @Expose
    int total_results;
    @SerializedName("articles")
            @Expose
    ArrayList<Article> articles;
    @SerializedName("sources")
@Expose
    ArrayList<Soruce> soruces;

    public ArrayList<Soruce> getSoruces() {
        return soruces;
    }

    public void setSoruces(ArrayList<Soruce> soruces) {
        this.soruces = soruces;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
