package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.NewsClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsCustomAdapter extends ArrayAdapter<NewsClass> {

    private Activity contex;
    private List<NewsClass> classList;
    public NewsCustomAdapter(@NonNull Activity context, List<NewsClass> classList) {
        super(context, R.layout.sample_of_news,classList);
        this.contex=context;
        this.classList=classList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_news,null,true);

        TextView des=view.findViewById(R.id.sample_of_news_description_id);
        TextView link=view.findViewById(R.id.sample_of_news_paper_id);
        ImageView image=view.findViewById(R.id.sample_of_news_image_id);

        NewsClass newsClass=classList.get(position);
        des.setText(newsClass.getDescription());
        link.setText(newsClass.getNews());

        Picasso.with(contex).
                load(classList.get(position).getImage()).
                placeholder(R.mipmap.ic_launcher_round).
                fit().into(image);

        return view;
    }
}
