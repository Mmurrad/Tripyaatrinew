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
import android.widget.Toast;

import com.example.tripyaatrinew.Adapter.StateCustomAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

public class StateListActivity extends AppCompatActivity {

    ListView state_listView;
    List<PlaceDetails> placeDetailsList;
    DatabaseReference databaseReference;
    String passkey;
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


        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();


        placeDetailsList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Details");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeDetailsList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);

                    placeDetailsList.add(placeDetails);
                }

                for(int i=0; i < placeDetailsList.size(); i++){
                    for(int j=0; j < placeDetailsList.size(); j++){
                        if(placeDetailsList.get(i).equals(placeDetailsList.get(j))){
                            placeDetailsList.remove(j);
                            //j;
                        }
                    }
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
                            else if(passkey.equals("health"))
                            {
                                intent.putExtra("key","health_key");
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
