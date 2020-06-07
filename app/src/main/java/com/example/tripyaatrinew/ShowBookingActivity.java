package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import com.example.tripyaatrinew.Adapter.PackageBookingAdapter;
import com.example.tripyaatrinew.model.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowBookingActivity extends AppCompatActivity {

    private ListView listView;
    private List<Booking> bookingList;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_booking);

        listView=findViewById(R.id.booking_list_id);


        Toolbar toolbar=findViewById(R.id.booking_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Package Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference= FirebaseDatabase.getInstance().getReference("Booking");

        bookingList=new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Booking booking=dataSnapshot1.getValue(Booking.class);
                    bookingList.add(booking);
                }
                PackageBookingAdapter packageBookingAdapter=new PackageBookingAdapter(ShowBookingActivity.this,bookingList);
                listView.setAdapter(packageBookingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
