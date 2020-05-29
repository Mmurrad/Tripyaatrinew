package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tripyaatrinew.Adapter.CityCustomAdapter;
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

public class City_list_Activity extends AppCompatActivity {

    ListView citylist;
    List<PlaceDetails> placelist;
    DatabaseReference databaseReference;
    String passvalue,passkey;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list_);
        citylist=findViewById(R.id.city_listview_id);

        Toolbar toolbar=findViewById(R.id.city_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select City");
        final Bundle bundle=getIntent().getExtras();
        try {
            passvalue=bundle.getString("state_list_key");
            passkey=bundle.getString("key");
        }catch (Exception e)
        {

        }


        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();



        placelist=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placelist.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                    try {
                        if(passvalue.equals(placeDetails.getState_name()))
                        {
                            placelist.add(placeDetails);
                        }
                    }catch (Exception e)
                    {

                    }

                }

                /*for(int i=0; i < placelist.size(); i++){
                    for(int j=0; j < placelist.size(); j++){
                        if(placelist.get(i).getCity_name().equals(placelist.get(j).getCity_name())){
                            placelist.remove(i);
                            //j;
                        }
                    }
                }*/

                final CityCustomAdapter cityCustomAdapter=new CityCustomAdapter(City_list_Activity.this,placelist);
                citylist.setAdapter(cityCustomAdapter);

                citylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String click=cityCustomAdapter.getItem(i).getCity_name();
                        databaseHelper.addData(click);
                        Intent intent=new Intent(City_list_Activity.this, ShowDetailsActivity.class);
                        intent.putExtra("city_key",click);
                        try{
                            if(passkey.equals("historical_key"))
                            {
                                intent.putExtra("value","Historical Place");
                                intent.putExtra("sub_key","No Category");
                            }
                            else if(passkey.equals("tourism_key"))
                            {
                                intent.putExtra("value","Tourism Place");
                                intent.putExtra("sub_key","No Category");
                            }
                            else if(passkey.equals("hotel_key"))
                            {
                                intent.putExtra("value","Hotel");
                                intent.putExtra("sub_key","No Category");
                            }
                            else if(passkey.equals("restaurent_key"))
                            {
                                intent.putExtra("value","Restaurent");
                                intent.putExtra("sub_key","No Category");
                            }
                            else if(passkey.equals("entertainment_movie_key"))
                            {
                                intent.putExtra("value","Entertainment Place");
                                intent.putExtra("sub_key","Movie");
                            }
                            else if(passkey.equals("entertainment_park_key"))
                            {
                                intent.putExtra("value","Entertainment Place");
                                intent.putExtra("sub_key","Park");
                            }
                            else if(passkey.equals("entertainment_event_key"))
                            {
                                intent.putExtra("value","Entertainment Place");
                                intent.putExtra("sub_key","Event");
                            }
                            else if(passkey.equals("cricket_sports_key"))
                            {
                                intent.putExtra("value","Sports Place");
                                intent.putExtra("sub_key","Cricket");
                            }
                            else if(passkey.equals("football_sports_key"))
                            {
                                intent.putExtra("value","Sports Place");
                                intent.putExtra("sub_key","Football");
                            }
                            else if(passkey.equals("child_sports_key"))
                            {
                                intent.putExtra("value","Sports Place");
                                intent.putExtra("sub_key","Child Stdium");
                            }
                            else if(passkey.equals("shopping_key"))
                            {
                                intent.putExtra("value","Shopping Mall");
                                intent.putExtra("sub_key","No Category");
                            }

                            else if(passkey.equals("bus_station_key"))
                            {
                                intent.putExtra("value","Station");
                                intent.putExtra("sub_key","Bus Station");
                            }
                            else if(passkey.equals("railway_station_key"))
                            {
                                intent.putExtra("value","Station");
                                intent.putExtra("sub_key","Railway Station");
                            }
                            else if(passkey.equals("airport_station_key"))
                            {
                                intent.putExtra("value","Station");
                                intent.putExtra("sub_key","Airport");
                            }
                            else if(passkey.equals("taxi_service_key"))
                            {
                                intent.putExtra("value","Public Service");
                                intent.putExtra("sub_key","Taxi");
                            }
                            else if(passkey.equals("police_service_key"))
                            {
                                intent.putExtra("value","Public Service");
                                intent.putExtra("sub_key","Police Station");
                            }
                            else if(passkey.equals("money_service_key"))
                            {
                                intent.putExtra("value","Public Service");
                                intent.putExtra("sub_key","Money Exchange");
                            }
                            else if(passkey.equals("health_key"))
                            {
                                intent.putExtra("value","Health");
                                intent.putExtra("sub_key","No Category");
                            }
                            else if(passkey.equals("more_key"))
                            {
                                intent.putExtra("value","Another");
                                intent.putExtra("sub_key","No Category");
                            }
                        }catch (Exception e)
                        {

                        }

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

