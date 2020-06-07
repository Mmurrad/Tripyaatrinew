package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripyaatrinew.HomeActivity;
import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.ShowDetailsActivity;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private List<PlaceDetails> doctordetails;
    public SearchAdapter(@NonNull Context context,List<PlaceDetails> doctordetails) {
        //super(context);
        this.context = context;
        this.doctordetails = doctordetails;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_of_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.MyViewHolder holder, int position) {
        final PlaceDetails doctor=doctordetails.get(position);

        holder.name.setText(doctor.getCity_name());
       holder.name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context, ShowDetailsActivity.class);
               intent.putExtra("city_key",doctor.getCity_name());
               intent.putExtra("value","search");
               intent.putExtra("sub_key","nothing");
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return doctordetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sample_search_id);
        }
    }
}
