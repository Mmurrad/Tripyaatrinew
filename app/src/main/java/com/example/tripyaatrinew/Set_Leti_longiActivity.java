package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.tripyaatrinew.model.Lati_Longi_Class;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Set_Leti_longiActivity extends AppCompatActivity {

    EditText place_name,longitude,latitude;
    Button save_button;
    ProgressBar progressBar;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__leti_longi);

        place_name=findViewById(R.id.lat_long_place_name_id);
        longitude=findViewById(R.id.longitude_id);
        latitude=findViewById(R.id.latitude_id);
        save_button=findViewById(R.id.lat_long_save_id);
        progressBar=findViewById(R.id.lat_long_progress_id);
        databaseReference= FirebaseDatabase.getInstance().getReference("Lati_longi");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=place_name.getText().toString();
                String longi=longitude.getText().toString();
                String lati=latitude.getText().toString();

                if(name.isEmpty())
                {
                    place_name.setError("Enter Place Name");
                    place_name.requestFocus();
                    return;
                }
                if(lati.isEmpty())
                {
                    latitude.setError("Enter Place Name");
                    latitude.requestFocus();
                    return;
                }
                if(longi.isEmpty())
                {
                    longitude.setError("Enter Place Name");
                    longitude.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    Lati_Longi_Class lati_longi_class=new Lati_Longi_Class(name,longi,lati);
                    databaseReference.child(name).setValue(lati_longi_class);
                    if(databaseReference!=null)
                    {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
