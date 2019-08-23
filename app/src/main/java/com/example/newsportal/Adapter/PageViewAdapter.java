package com.example.newsportal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.newsportal.Activity.MainActivity;
import com.example.newsportal.Activity.Utility;
import com.example.newsportal.bean.Soruce;
import com.example.newsportal.R;


public class PageViewAdapter extends PagerAdapter {
    int general = 1 , sports = 2 , technology = 3 , business = 0;
    ArrayList<Soruce> data;
    Context context;
    Drawable drawable;

    public PageViewAdapter(ArrayList<Soruce> data, Context context , Drawable drawable) {
        this.data = data;
        this.context = context;
        this.drawable = drawable;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.single_item_soruce , container , false);
        TextView color , color1;
        TextView name , description, category;
        CardView card;
        color = v.findViewById(R.id.color);
        color1 = v.findViewById(R.id.color1);
        name = v.findViewById(R.id.name);
        description = v.findViewById(R.id.description);
        category = v.findViewById(R.id.category);
        drawable = Utility.getRandomDrawbleColor();
        card = v.findViewById(R.id.card);
        color.setBackground(drawable);
        color1.setBackground(drawable);
        name.setText(data.get(position).getName());
        description.setText(data.get(position).getDescription());
        category.setText(data.get(position).getCategory());
        category.setAllCaps(true);
        switch (data.get(position).getCategory()){
            case "business":
                category.setTextColor(context.getResources().getColor(R.color.business));
                break;
            case "general":
                category.setTextColor(context.getResources().getColor(R.color.general));
                break;
            case "sports":
                category.setTextColor(context.getResources().getColor(R.color.sports));
                break;
            default:
                category.setTextColor(context.getResources().getColor(R.color.technology));


        }
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soruce selected = data.get(position);
                Intent intent = new Intent(context , MainActivity.class);
                intent.putExtra("source" , selected);
                context.startActivity(intent);
            }
        });
      //  category.setBackground(Utility.getRandomDrawbleColor());

        container.addView(v , 0);


        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
