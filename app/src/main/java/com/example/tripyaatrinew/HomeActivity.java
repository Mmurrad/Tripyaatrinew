package com.example.tripyaatrinew;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;

import com.example.tripyaatrinew.Adapter.CityCustomAdapter;
import com.example.tripyaatrinew.Adapter.PlaceDetailsCustomAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.example.tripyaatrinew.model.VisitorsCount;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView about_admin;
    Spinner spinner;
    ConstraintLayout constraintLayout;
    CardView historical_place,tourism_place,restaurent,hotel,entertainment,sports,shopping,station,service,health,news,admin_text,more_text;
    String passvalue;
    String passkey;
    Spinner searchspinner;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;


    List<String> placeDetailsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        databaseHelper.addData("HomePage");


        try{
            final Bundle bundle=getIntent().getExtras();
            passvalue=bundle.getString("admin_key");
            passkey=bundle.getString("count");
        }catch (Exception e)
        {
           // Toast.makeText(HomeActivity.this,""+e,Toast.LENGTH_LONG).show();
        }


        about_admin=findViewById(R.id.about_admin_id);

        spinner=findViewById(R.id.spinner_id);
        placeDetailsList=new ArrayList<>();


        historical_place=findViewById(R.id.historical_id);
        tourism_place=findViewById(R.id.tourism_id);
        hotel=findViewById(R.id.hotel_id);
        restaurent=findViewById(R.id.restaurent_id);
        entertainment=findViewById(R.id.entertainment_id);
        sports=findViewById(R.id.sports_id);
        shopping=findViewById(R.id.shopping_id);
        station=findViewById(R.id.station_id);
        service=findViewById(R.id.service_id);
        health=findViewById(R.id.health_id);
        news=findViewById(R.id.news_id);
        more_text=findViewById(R.id.more_id);


        searchspinner=findViewById(R.id.searchview_id);

        databaseReference=FirebaseDatabase.getInstance().getReference("Details");

        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(this,R.array.details,R.layout.texview_color);
        arrayAdapter.setDropDownViewResource(R.layout.texview_color);
        spinner.setAdapter(arrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeDetailsList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                    String city=placeDetails.getCity_name();
                    placeDetailsList.add(city);
                }
                searchspinner.setAdapter(new ArrayAdapter<>(HomeActivity.this,R.layout.support_simple_spinner_dropdown_item,placeDetailsList));

                searchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item=adapterView.getItemAtPosition(i).toString();
                        Intent intent=new Intent(HomeActivity.this,ShowDetailsActivity.class);
                        intent.putExtra("city_key",item);
                        intent.putExtra("value","search");
                        intent.putExtra("sub_key","nothing");
                        startActivity(intent);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        admin_text=findViewById(R.id.admin_id);


        about_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Show_About_App_Activity.class);
                //explore.setTextColor(getResources().getColor(R.color.btnGreen));
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        try{
            if(passvalue.equals("admin"))
            {
                admin_text.setVisibility(View.VISIBLE);
            }
            else if(passvalue.equals("skip"))
            {
                admin_text.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e)
        {

        }


        admin_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,AdminDashBoardActivity.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinner_text=adapterView.getItemAtPosition(i).toString();
                if(spinner_text.equals("Historical Place"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","historical_place");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Tourism Place"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","tourism_place");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Hotel"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","hotel");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Entertainment Place"))
                {
                    Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                    startActivity(intent);
                }
                else if(spinner_text.equals("Restaurent"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","restaurent");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Sports Place"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","sports");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Shopping Mall"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","shopping");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Station"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","station");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Public Service"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","service");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Health"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","health");
                    startActivity(intent);
                }
                else if(spinner_text.equals("Another"))
                {
                    Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                    intent.putExtra("details","more");
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        historical_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","historical_place");
                startActivity(intent);
            }
        });
        tourism_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","tourism_place");
                startActivity(intent);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","hotel");
                startActivity(intent);
            }
        });
        restaurent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","restaurent");
                startActivity(intent);
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                intent.putExtra("key","visible_ent");
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                intent.putExtra("key","visible_sports");
                startActivity(intent);
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","shopping");
                startActivity(intent);
            }
        });
        station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                intent.putExtra("key","visible_station");
                startActivity(intent);
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                intent.putExtra("key","visible_service");
                startActivity(intent);
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","health");
                startActivity(intent);
            }
        });
        more_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
                intent.putExtra("details","more");
                startActivity(intent);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,NewsActivity.class);
               // intent.putExtra("key","news");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.historical_id) {
            // Handle the camera action
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","historical_place");
            startActivity(intent);
        } else if (id == R.id.tourism_id) {
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","tourism_place");
            startActivity(intent);
        } else if (id == R.id.restaurent_id) {
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","restaurent");
            startActivity(intent);
        } else if (id == R.id.hotel_id) {
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","hotel");
            startActivity(intent);
        } else if (id == R.id.entertainment_id) {
            Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
            intent.putExtra("key","visible_ent");
            startActivity(intent);
        } else if (id == R.id.sports_id) {
            Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
            intent.putExtra("key","visible_sports");
            startActivity(intent);
        } else if (id == R.id.service_id) {
            Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
            intent.putExtra("key","visible_service");
            startActivity(intent);
        }  else if (id == R.id.shopping_id) {
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","shopping");
            startActivity(intent);
        }  else if (id == R.id.station_id) {
            Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
            intent.putExtra("key","visible_station");
            startActivity(intent);
        }  else if (id == R.id.health_id) {
            Intent intent=new Intent(HomeActivity.this,StateListActivity.class);
            intent.putExtra("details","health");
            startActivity(intent);
        }  else if (id == R.id.news_id) {
            Intent intent=new Intent(HomeActivity.this,NewsActivity.class);
            //intent.putExtra("key","news");
            startActivity(intent);
        }  else if (id == R.id.history_id) {
            Intent intent=new Intent(HomeActivity.this,HistoryActivity.class);
            startActivity(intent);
        }  else if (id == R.id.document_id) {
            Intent intent=new Intent(HomeActivity.this,ShowDocumentActivity.class);
            //intent.putExtra("key","documents");
            startActivity(intent);

        }  else if (id == R.id.favourite) {
            Intent intent=new Intent(HomeActivity.this,ShowDetailsActivity.class);
            intent.putExtra("city_key","fav1");
            intent.putExtra("value","fav2");
            intent.putExtra("sub_key","fav3");
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(HomeActivity.this,SendMessageActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
