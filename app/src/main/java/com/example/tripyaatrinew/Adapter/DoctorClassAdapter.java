package com.example.tripyaatrinew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripyaatrinew.R;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoctorClassAdapter extends RecyclerView.Adapter<DoctorClassAdapter.MyViewHolder> {
    private Context context;
    private List<PlaceDetails> doctordetails;
    public DoctorClassAdapter(@NonNull Context context,List<PlaceDetails> doctordetails) {
        //super(context);
        this.context = context;
        this.doctordetails = doctordetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_of_doctor,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PlaceDetails doctor=doctordetails.get(position);

        holder.name.setText(doctor.getName());
        holder.specialist.setText(doctor.getSpecialist());
        holder.graduation.setText(doctor.getGraduation());
        holder.phone.setText(doctor.getPhone());
        holder.charge.setText(doctor.getCharges());
        holder.address.setText(doctor.getAddress());

        Picasso.with(context).
                load(doctordetails.get(position).getImage()).
                placeholder(R.mipmap.ic_launcher_round).
                fit().into(holder.doctor_image);
    }

    @Override
    public int getItemCount() {
        return doctordetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,specialist,phone,graduation,charge,address;
        ImageView doctor_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.sample_doctor_name_id);
            specialist=itemView.findViewById(R.id.sample_doctor_specilist_id);
            phone=itemView.findViewById(R.id.sample_doctor_phone_id);
            graduation=itemView.findViewById(R.id.sample_doctor_graduation_id);
            charge=itemView.findViewById(R.id.sample_charges_id);
            address=itemView.findViewById(R.id.sample_doctor_address_id);

            doctor_image=itemView.findViewById(R.id.doctor_image_id);
        }
    }
}
