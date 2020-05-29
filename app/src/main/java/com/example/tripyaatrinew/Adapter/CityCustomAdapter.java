package com.example.tripyaatrinew.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.PlaceDetails;

import java.util.List;

public class CityCustomAdapter extends ArrayAdapter<PlaceDetails> {
    private Activity contex;
    private List<PlaceDetails> placeDetails;
    public CityCustomAdapter(@NonNull Activity context, List<PlaceDetails> placeDetails) {
        super(context, R.layout.sample_of_city,placeDetails);
        this.contex=context;
        this.placeDetails=placeDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_city,null,true);

        TextView city=view.findViewById(R.id.sample_city_name_id);
        TextView address=view.findViewById(R.id.sample_address_id);
        PlaceDetails details=placeDetails.get(position);
        city.setText(details.getCity_name());
        address.setText(details.getAddress());
        return view;
    }
}
