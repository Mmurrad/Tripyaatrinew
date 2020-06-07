package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripyaatrinew.Adapter.DoctorClassAdapter;
import com.example.tripyaatrinew.Adapter.PlaceDetailsCustomAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlaceDetailsCustomAdapter placeDetailsCustomAdapter;
    List<PlaceDetails> placeDetailsList;
    List<PlaceDetails> place_fav_unfav;
    DatabaseReference databaseReference,doctordatabase;
    DatabaseHelper databaseHelper;
    String passkey,passvalue,pass_sub,testkey;
    Button gallery_button,booking_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gallery_button=findViewById(R.id.sample_state_gallery_id);
        booking_button=findViewById(R.id.click_for_book_id);

        Toolbar toolbar=findViewById(R.id.details_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        databaseHelper.addData("ShowPlaces");

        databaseReference= FirebaseDatabase.getInstance().getReference("Details");
        doctordatabase= FirebaseDatabase.getInstance().getReference("DoctorDetails");

        try{
            final  Bundle bundle=getIntent().getExtras();
            passkey=bundle.getString("value");
            passvalue=bundle.getString("city_key");
            pass_sub=bundle.getString("sub_key");
            testkey=bundle.getString("test_key");
        }catch (Exception e)
        {

        }

        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_from_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        Toast.makeText(getApplicationContext(),""+passkey,Toast.LENGTH_LONG).show();

        placeDetailsList=new ArrayList<>();
        place_fav_unfav=new ArrayList<>();


        booking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowDetailsActivity.this,BookingActivity.class);
                startActivity(intent);
            }
        });

        try{
            if(testkey.equals("doctor"))
            {
                doctordatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        placeDetailsList.clear();
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            final PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                            if(pass_sub.equals(placeDetails.getPlace_sub())&&passkey.equals(placeDetails.getPlace_heading())&&passvalue.equals(placeDetails.getCity_name()))
                            {
                                placeDetailsList.add(placeDetails);
                            }
                        }
                        DoctorClassAdapter doctorClassAdapter =new DoctorClassAdapter(ShowDetailsActivity.this,placeDetailsList);
                        recyclerView.setAdapter(doctorClassAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            else if(testkey.equals("nothing"))
            {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        placeDetailsList.clear();
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            final PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                            try{
                                if(pass_sub.equals(placeDetails.getPlace_sub())&&passkey.equals(placeDetails.getPlace_heading())&&passvalue.equals(placeDetails.getCity_name()))
                                {
                                    placeDetailsList.add(placeDetails);
                                }
                                else if(pass_sub.equals("all_category")&&passkey.equals("all_place")&&passvalue.equals("all_city")){

                                    placeDetailsList.add(placeDetails);
                                }
                                else if(pass_sub.equals(placeDetails.getPlace_sub())&&passkey.equals(placeDetails.getPlace_heading())&&passvalue.equals("state_skip_city")){

                                    placeDetailsList.add(placeDetails);
                                }
                                else if(pass_sub.equals(placeDetails.getPlace_sub())&&passkey.equals(placeDetails.getPlace_heading())&&passvalue.equals("city_name_skip")){

                                    placeDetailsList.add(placeDetails);
                                }
                                else if(pass_sub.equals("nothing")&&passkey.equals("search")&&passvalue.equals(placeDetails.getCity_name())){

                                    placeDetailsList.add(placeDetails);
                                }
                                else if(pass_sub.equals("fav3")&&passkey.equals("fav2")&&passvalue.equals("fav1"))
                                {
                                    DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("fav_unfav");

                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            placeDetailsList.clear();
                                            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                                            {
                                                PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                                                placeDetailsList.add(placeDetails);
                                            }
                                            placeDetailsCustomAdapter=new PlaceDetailsCustomAdapter(ShowDetailsActivity.this,placeDetailsList);
                                            recyclerView.setAdapter(placeDetailsCustomAdapter);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }

                            }catch (Exception e)
                            {

                            }
                        }
                        placeDetailsCustomAdapter=new PlaceDetailsCustomAdapter(ShowDetailsActivity.this,placeDetailsList);
                        recyclerView.setAdapter(placeDetailsCustomAdapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        }catch (Exception e)
        {

        }

    }
}

