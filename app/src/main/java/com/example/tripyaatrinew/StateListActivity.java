package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.tripyaatrinew.Adapter.StateCustomAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static java.lang.System.in;

public class StateListActivity extends AppCompatActivity {

    ListView state_listView;
    private AdView mAdView;
    List<PlaceDetails> placeDetailsList;
    DatabaseReference databaseReference;
    String passkey;
    int count;
    TextView state_text;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);
        state_listView=findViewById(R.id.state_listview_id);
        try {
            final Bundle bundle=getIntent().getExtras();
            passkey=bundle.getString("details");
        }catch (Exception e)
        {
        }


        Toolbar toolbar=findViewById(R.id.state_toolbar_id);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Select State");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAdView = findViewById(R.id.state_up_adView);
        state_text=findViewById(R.id.state_skip_id);
        AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.setAdUnitId(getString(R.string.admob_banner_unit_id));
        mAdView.loadAd(adRequest);


        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(this,R.anim.layout_animation_from_right);
        state_listView.setLayoutAnimation(layoutAnimationController);

        state_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StateListActivity.this,ShowDetailsActivity.class);
                intent.putExtra("city_key","state_skip_city");
                if(passkey.equals("historical_place"))
                {
                    intent.putExtra("value","Historical Place");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("tourism_place"))
                {
                    intent.putExtra("value","Tourism Place");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("hotel"))
                {
                    intent.putExtra("value","Hotel");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("restaurent"))
                {
                    intent.putExtra("value","Restaurent");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("entertainment_movie"))
                {
                    intent.putExtra("value","Entertainment Place");
                    intent.putExtra("sub_key","Movie");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("entertainment_park"))
                {
                    intent.putExtra("value","Entertainment Place");
                    intent.putExtra("sub_key","Park");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("entertainment_event"))
                {
                    intent.putExtra("value","Entertainment Place");
                    intent.putExtra("sub_key","Event");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("cricket_sports"))
                {
                    intent.putExtra("value","Sports Place");
                    intent.putExtra("sub_key","Cricket");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("football_sports"))
                {
                    intent.putExtra("value","Sports Place");
                    intent.putExtra("sub_key","Football");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("child_sports"))
                {
                    intent.putExtra("value","Sports Place");
                    intent.putExtra("sub_key","Child Stdium");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("shopping"))
                {
                    intent.putExtra("value","Shopping Mall");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("bus_station"))
                {
                    intent.putExtra("value","Station");
                    intent.putExtra("sub_key","Bus Station");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("railway_station"))
                {
                    intent.putExtra("value","Station");
                    intent.putExtra("sub_key","Railway Station");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("airport_station"))
                {
                    intent.putExtra("value","Station");
                    intent.putExtra("sub_key","Airport");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("taxi_service"))
                {
                    intent.putExtra("value","Public Service");
                    intent.putExtra("sub_key","Taxi");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("police_service"))
                {
                    intent.putExtra("value","Public Service");
                    intent.putExtra("sub_key","Police Station");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("money_service"))
                {
                    intent.putExtra("value","Public Service");
                    intent.putExtra("sub_key","Money Exchange");
                    intent.putExtra("test_key","nothing");
                }
                else if(passkey.equals("doctor"))
                {
                    intent.putExtra("value","Health");
                    intent.putExtra("sub_key","Doctor Clinic");
                    intent.putExtra("test_key","doctor");
                }
                else if(passkey.equals("hospital"))
                {
                    intent.putExtra("value","Health");
                    intent.putExtra("sub_key","Hospital");
                    intent.putExtra("test_key","doctor");
                }
                else if(passkey.equals("more"))
                {
                    intent.putExtra("value","Another");
                    intent.putExtra("sub_key","No Category");
                    intent.putExtra("test_key","nothing");
                }
                startActivity(intent);
            }
        });

        placeDetailsList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("StateList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeDetailsList.clear();
                int i = 1;
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                    placeDetailsList.add(placeDetails);

                    //Log.d("check",placeDetails.getState_name().substring(0,1));


                }




                final StateCustomAdapter stateCustomAdapter=new StateCustomAdapter(StateListActivity.this,placeDetailsList);
                state_listView.setAdapter(stateCustomAdapter);

                state_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String click=stateCustomAdapter.getItem(i).getState_name();
                        databaseHelper.addData(click);
                        Intent intent=new Intent(StateListActivity.this,City_list_Activity.class);
                        try{
                            if(passkey.equals("historical_place"))
                            {
                                intent.putExtra("key","historical_key");
                            }
                            else if(passkey.equals("tourism_place"))
                            {
                                intent.putExtra("key","tourism_key");
                            }
                            else if(passkey.equals("hotel"))
                            {
                                intent.putExtra("key","hotel_key");
                            }
                            else if(passkey.equals("restaurent"))
                            {
                                intent.putExtra("key","restaurent_key");
                            }
                            else if(passkey.equals("entertainment_movie"))
                            {
                                intent.putExtra("key","entertainment_movie_key");

                            }
                            else if(passkey.equals("entertainment_park"))
                            {
                                intent.putExtra("key","entertainment_park_key");
                            }
                            else if(passkey.equals("entertainment_event"))
                            {
                                intent.putExtra("key","entertainment_event_key");
                            }
                            else if(passkey.equals("cricket_sports"))
                            {
                                intent.putExtra("key","cricket_sports_key");
                            }
                            else if(passkey.equals("football_sports"))
                            {
                                intent.putExtra("key","football_sports_key");
                            }
                            else if(passkey.equals("child_sports"))
                            {
                                intent.putExtra("key","child_sports_key");
                            }
                            else if(passkey.equals("shopping"))
                            {
                                intent.putExtra("key","shopping_key");
                            }
                            else if(passkey.equals("bus_station"))
                            {
                                intent.putExtra("key","bus_station_key");
                            }
                            else if(passkey.equals("railway_station"))
                            {
                                intent.putExtra("key","railway_station_key");
                            }
                            else if(passkey.equals("airport_station"))
                            {
                                intent.putExtra("key","airport_station_key");
                            }
                            else if(passkey.equals("taxi_service"))
                            {
                                intent.putExtra("key","taxi_service_key");
                            }
                            else if(passkey.equals("police_service"))
                            {
                                intent.putExtra("key","police_service_key");
                            }
                            else if(passkey.equals("money_service"))
                            {
                                intent.putExtra("key","money_service_key");
                            }
                            else if(passkey.equals("doctor"))
                            {
                                intent.putExtra("key","doctor_key");
                            }
                            else if(passkey.equals("hospital"))
                            {
                                intent.putExtra("key","hospital_key");
                            }
                            else if(passkey.equals("more"))
                            {
                                intent.putExtra("key","more_key");
                            }

                        }catch (Exception e)
                        {

                        }
                        intent.putExtra("state_list_key",click);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
