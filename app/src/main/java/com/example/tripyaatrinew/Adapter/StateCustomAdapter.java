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

public class StateCustomAdapter extends ArrayAdapter<PlaceDetails> {
    private Activity context;
    private List<PlaceDetails> placeDetails;

    public StateCustomAdapter(@NonNull Activity context, List<PlaceDetails> placeDetails) {
        super(context, R.layout.sample_of_statelist,placeDetails);
        this.context=context;
        this.placeDetails=placeDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_statelist,null,true);

        TextView state_name=view.findViewById(R.id.sample_state_name_id);
        TextView area=view.findViewById(R.id.sample_area_id);

        PlaceDetails details=placeDetails.get(position);
        state_name.setText(details.getState_name());
        area.setText(details.getArea()+" (squre km)");

        return view;
    }
}
