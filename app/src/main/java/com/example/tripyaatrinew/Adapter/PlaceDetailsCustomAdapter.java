package com.example.tripyaatrinew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripyaatrinew.MapsActivity;
import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.ShowGalleryActivity;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaceDetailsCustomAdapter extends RecyclerView.Adapter<PlaceDetailsCustomAdapter.MyViewHolder> {
    private Context context;
    private List<PlaceDetails> placeDetails;

    public PlaceDetailsCustomAdapter(Context context, List<PlaceDetails> placeDetails) {
        this.context = context;
        this.placeDetails = placeDetails;
    }


    @NonNull
    @Override
    public PlaceDetailsCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_of_details_recyclerview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceDetailsCustomAdapter.MyViewHolder holder, int position) {
        final PlaceDetails place=placeDetails.get(position);
        holder.place_name.setText(place.getPlace_name());
        holder.address.setText(place.getAddress());
        holder.phone.setText(place.getPhone());
        holder.website.setText(place.getWeb());
        holder.about.setText(place.getAbout_place());

        Picasso.with(context).
                load(placeDetails.get(position).getImage()).
                placeholder(R.mipmap.ic_launcher_round).
                fit().into(holder.imageView);
        holder.gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowGalleryActivity.class);
                intent.putExtra("gallery_key",place.getPlace_name());
                intent.putExtra("hedding_key",place.getPlace_heading());
                context.startActivity(intent);
            }
        });
        holder.map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MapsActivity.class);
                intent.putExtra("map_key",place.getPlace_name());
                context.startActivity(intent);
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("fav_unfav");
                if(b)
                {
                    databaseReference.child(place.getPhone()).setValue(place);
                }
                else {
                    databaseReference.child(place.getPhone()).setValue(null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView place_name,address,phone,website,about;
        Button gallery_button,map_button;
        ImageView imageView;
        CheckBox checkBox;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            place_name=itemView.findViewById(R.id.sample_re_area_id);
            address=itemView.findViewById(R.id.sample_re_in_address_id);
            phone=itemView.findViewById(R.id.sample_re_in_phone_id);
            website=itemView.findViewById(R.id.sample_re_in_website_id);
            about=itemView.findViewById(R.id.sample_re_in_about_id);
            imageView=itemView.findViewById(R.id.show_image_id);
            imageView=itemView.findViewById(R.id.show_image_id);

            gallery_button=itemView.findViewById(R.id.sample_state_gallery_id);
            map_button=itemView.findViewById(R.id.sample_map_id);

            checkBox=itemView.findViewById(R.id.sample_fav_unfav_id);
        }
    }
}
