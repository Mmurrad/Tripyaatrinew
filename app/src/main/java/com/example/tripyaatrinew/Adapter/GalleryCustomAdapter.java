package com.example.tripyaatrinew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.GalleryClass;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryCustomAdapter extends RecyclerView.Adapter<GalleryCustomAdapter.MyViewHolder> {

    private Context context;
    private List<GalleryClass> galleryClasses;

    public GalleryCustomAdapter(Context context, List<GalleryClass> galleryClasses) {
        this.context = context;
        this.galleryClasses = galleryClasses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_of_gallery,parent,false);
        return new GalleryCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context).
                load(galleryClasses.get(position).getImage()).
                placeholder(R.mipmap.ic_launcher_round).
                fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return galleryClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.show_gallery_image_id);
        }
    }
}
