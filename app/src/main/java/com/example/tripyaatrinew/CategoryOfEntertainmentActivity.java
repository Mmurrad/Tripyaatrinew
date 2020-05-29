package com.example.tripyaatrinew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CategoryOfEntertainmentActivity extends AppCompatActivity {

    CardView movie_card,park_card,event_card,cricket_card,football_card,child_card,bus_card,railway_card,airport_card,taxi_card,police_card,money_card;
    String passkey;
    LinearLayout entertainment,sports,station,service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_of_entertainment);

        Toolbar toolbar=findViewById(R.id.category_toolbar_id);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movie_card=findViewById(R.id.movie_id);
        park_card=findViewById(R.id.park_id);
        event_card=findViewById(R.id.event_id);
        entertainment=findViewById(R.id.entertainment_visbilty_id);
        sports=findViewById(R.id.sports_visbilty_id);
        station=findViewById(R.id.station_visbilty_id);
        service=findViewById(R.id.service_visbilty_id);


        cricket_card=findViewById(R.id.cricket_id);
        football_card=findViewById(R.id.football_id);
        child_card=findViewById(R.id.child_ground_id);
        bus_card=findViewById(R.id.bus_station_id);
        railway_card=findViewById(R.id.railway_id);
        airport_card=findViewById(R.id.airport_id);
        taxi_card=findViewById(R.id.taxi_id);
        police_card=findViewById(R.id.police_station_id);
        money_card=findViewById(R.id.money_exchange_id);

        final Bundle bundle=getIntent().getExtras();
        passkey=bundle.getString("key");

        if(passkey.equals("visible_ent"))
        {
            entertainment.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Entertainment Category");
        }
        else if(passkey.equals("visible_sports"))
        {
            sports.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Sports Category");
        }
        else if(passkey.equals("visible_station"))
        {
            station.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Station Category");
        }

        else if(passkey.equals("visible_service"))
        {
            service.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Service Category");
        }

        //service onclick Listener
        taxi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","taxi_service");
                startActivity(intent);
            }
        });
        police_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","police_service");
                startActivity(intent);
            }
        });
        money_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","money_service");
                startActivity(intent);
            }
        });


        //station onclik Listener
        bus_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","bus_station");
                startActivity(intent);
            }
        });
        railway_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","railway_station");
                startActivity(intent);
            }
        });
        airport_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","airport_station");
                startActivity(intent);
            }
        });




        //Sporst Onclik Listener
        cricket_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","cricket_sports");
                startActivity(intent);
            }
        });
        football_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","football_sports");
                startActivity(intent);
            }
        });
        child_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","child_sports");
                startActivity(intent);
            }
        });




        //Entertainment Onclick Listener
        movie_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","entertainment_movie");
                startActivity(intent);
            }
        });

        park_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","entertainment_park");
                startActivity(intent);
            }
        });
        event_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryOfEntertainmentActivity.this,StateListActivity.class);
                intent.putExtra("details","entertainment_event");
                startActivity(intent);
            }
        });
    }
}
