package com.example.newsportal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.newsportal.bean.Article;
import com.example.newsportal.Activity.Details;
import com.example.newsportal.R;

import java.util.ArrayList;

import static android.view.View.GONE;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Article> data;

    public NewsViewAdapter(Context context, ArrayList<Article> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_news_list , viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
    final Article article = data.get(i);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utility.getRandomDrawbleColor());
        requestOptions.error(Utility.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        Glide.with(context).load(article.getImage_url()).apply(requestOptions).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Toast.makeText(context , "Load Failed " , Toast.LENGTH_LONG).show();
                viewHolder.progressBar.setVisibility(GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Toast.makeText(context , "Loaded " , Toast.LENGTH_LONG).show();
                viewHolder.progressBar.setVisibility(GONE);
                return false;
            }
        })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.img);
        viewHolder.title.setText(article.getTitle());
        viewHolder.author.setText(article.getAuthor());
        viewHolder.description.setText(article.getDescription());
        viewHolder.source.setText(article.getSource_name().getName());
        viewHolder.time.setText("\u2022" + Utility.DateToTimeFormat(article.getPublished_at()) );
        viewHolder.published_at.setText(Utility.DateFormat(article.getPublished_at()));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , Details.class);
                intent.putExtra("article" , article);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView author;
        TextView title;
        TextView source;
        TextView description;
        TextView time;
        TextView published_at;
        ProgressBar progressBar;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.news_img);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.Title);
            description = itemView.findViewById(R.id.desc);
            source = itemView.findViewById(R.id.source);
            cardView = itemView.findViewById(R.id.card_view);
            time = itemView.findViewById(R.id.time);
            published_at = itemView.findViewById(R.id.published_at);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
