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
import com.example.tripyaatrinew.model.Booking;
import com.example.tripyaatrinew.model.Comment;

import java.util.List;

public class PackageBookingAdapter extends ArrayAdapter<Booking> {
    private Activity contex;
    private List<Booking> bookingList;
    public PackageBookingAdapter(@NonNull Activity context, List<Booking> bookingList) {
        super(context, R.layout.sample_of_packge_booking);
        this.contex=context;
        this.bookingList=bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=contex.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_of_packge_booking,null,true);

        TextView name=view.findViewById(R.id.sample_of_name_id);
        TextView phone=view.findViewById(R.id.sample_of_phone_id);
        TextView s_location=view.findViewById(R.id.sample_of_starting_id);
        TextView visit=view.findViewById(R.id.sample_of_visit_id);
        TextView email=view.findViewById(R.id.sample_of_email_id);
        TextView person=view.findViewById(R.id.sample_of_person_id);

        Booking details=bookingList.get(position);
        name.setText(details.getName());
        phone.setText(details.getPhone());
        s_location.setText(details.getStarting());
        visit.setText(details.getVisit());
        email.setText(details.getEmail());
        person.setText(details.getPerson());
        return view;
    }
}
