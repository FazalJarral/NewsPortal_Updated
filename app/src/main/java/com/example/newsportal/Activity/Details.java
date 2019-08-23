package com.example.newsportal.Activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.newsportal.Activity.Utility;
import com.example.newsportal.R;
import com.example.newsportal.bean.Article;

import static android.view.View.GONE;

public class Details extends AppCompatActivity {
    ImageView imageView;
    TextView title;
    TextView content;
    Article article;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.news_img);
        title = findViewById(R.id.Title);
        progressBar = findViewById(R.id.progress_bar);
        content = findViewById(R.id.content);
        if (getIntent()!=null)
        {
        article = (Article)getIntent().getSerializableExtra("article");
        }

        title.setText(article.getTitle());
        content.setText(article.getContent());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utility.getRandomDrawbleColor());
        requestOptions.error(Utility.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        Glide.with(getApplicationContext()).load(article.getImage_url()).apply(requestOptions).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Toast.makeText(getApplicationContext(), "Image Failed To Load, Please Check Internet", Toast.LENGTH_SHORT).show();
               progressBar.setVisibility(GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(GONE);

                return false;
            }
        })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }
}
