package com.example.newsportal.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Article implements Serializable {
    @SerializedName("source")
    Soruce source_name;

    Soruce id;
    @SerializedName("author")
    String author;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("urlToImage")
    String image_url;
    @SerializedName("publishedAt")
    String published_at;
    @SerializedName("content")
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public Soruce getSource_name() {
        return source_name;
    }

    public void setSource_name(Soruce source_name) {
        this.source_name = source_name;
    }

    public Soruce getId() {
        return id;
    }

    public void setId(Soruce id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
