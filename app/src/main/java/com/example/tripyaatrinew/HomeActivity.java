package com.example.tripyaatrinew;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tripyaatrinew.Adapter.CityCustomAdapter;
import com.example.tripyaatrinew.Adapter.PlaceDetailsCustomAdapter;
import com.example.tripyaatrinew.Adapter.SearchAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.example.tripyaatrinew.model.VisitorsCount;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.facebook.FacebookSdk.setAdvertiserIDCollectionEnabled;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView explore_text,about_admin;
    Spinner spinner;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ScrollView scrollView,scrollView_s;
    private AdView mAdView,mAdView2;
    CardView historical_place,tourism_place,restaurent,hotel,entertainment,sports,shopping,station,service,health,news,admin_text,more_text;
    String passvalue;
    String passkey,comment_key;
    Spinner searchspinner;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;
    CoordinatorLayout coordinatorLayout;
    RecyclerView recyclerView;


    List<PlaceDetails> placeDetailsList;


    TextView app_name_text,about_app_text,admin_name_text,admin_phone_text,admin_email_text,admin_website_text;
    DatabaseReference databaseReference1;
    DatabaseHelper databaseHelper1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        mAdView2 = findViewById(R.id.adView2nd);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        databaseHelper.addData("HomePage");


        ///About app
        app_name_text=findViewById(R.id.get_app_name_id);
        about_app_text=findViewById(R.id.get_about_app_id);
        admin_name_text=findViewById(R.id.get_admim_name_id);
        admin_phone_text=findViewById(R.id.get_admim_phone_id);
        admin_email_text=findViewById(R.id.get_admim_email_id);
        admin_website_text=findViewById(R.id.get_admim_website_id);

        scrollView_s=findViewById(R.id.scrollview_id);
        recyclerView=findViewById(R.id.searchRv);

        databaseReference1=FirebaseDatabase.getInstance().getReference().child("AboutApp").child("01521307785");

        databaseHelper1=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase1=databaseHelper.getWritableDatabase();
        databaseHelper.addData("AboutAppAndAdmin");


        try{
            final Bundle bundle=getIntent().getExtras();
            passvalue=bundle.getString("admin_key");
            passkey=bundle.getString("count");
            comment_key=bundle.getString("comment");
        }catch (Exception e)
        {
           // Toast.makeText(HomeActivity.this,""+e,Toast.LENGTH_LONG).show();
        }


        about_admin=findViewById(R.id.about_admin_id);
        explore_text=findViewById(R.id.explore_id);

        try{
            if(passvalue.equals("admin")||passvalue.equals("skip"))
            {
                explore_text.setTextColor(getResources().getColor(R.color.lightsalmon));
            }
        }catch (Exception e)
        {

        }



        spinner=findViewById(R.id.spinner_id);
        placeDetailsList=new ArrayList<>();

        linearLayout=findViewById(R.id.mainpage_id);
        scrollView=findViewById(R.id.about_id);

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

        coordinatorLayout=findViewById(R.id.homeparent_id);

        admin_text=findViewById(R.id.admin_id);

        //searchspinner=findViewById(R.id.searchview_id);

        databaseReference=FirebaseDatabase.getInstance().getReference("Details");

        ArrayAdapter arrayAdapter= ArrayAdapter.createFromResource(this,R.array.details,R.layout.texview_color);
        arrayAdapter.setDropDownViewResource(R.layout.texview_color);
        spinner.setAdapter(arrayAdapter);

        explore_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
                about_admin.setTextColor(getResources().getColor(R.color.txt_color_home));
                explore_text.setTextColor(getResources().getColor(R.color.btnGreen));
               scrollView.setVisibility(View.GONE);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        placeDetailsList.clear();
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                            //String city=placeDetails.getCity_name();
                            placeDetailsList.add(placeDetails);
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
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                placeDetailsList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    PlaceDetails placeDetails=dataSnapshot1.getValue(PlaceDetails.class);
                    //String city=placeDetails.getCity_name();
                    placeDetailsList.add(placeDetails);
                }
               // searchspinner.setAdapter(new ArrayAdapter<>(HomeActivity.this,R.layout.support_simple_spinner_dropdown_item,placeDetailsList));

               /* searchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                });*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        about_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ntent intent=new Intent(HomeActivity.this,Show_About_App_Activity.class);
                about_admin.setTextColor(getResources().getColor(R.color.btnGreen));
                explore_text.setTextColor(getResources().getColor(R.color.txt_color_home));
                linearLayout.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try{
                            String about_app=dataSnapshot.child("about_app").getValue().toString();
                            String app_name=dataSnapshot.child("appname").getValue().toString();
                            String admin_name=dataSnapshot.child("admin_name").getValue().toString();
                            String admin_ph=dataSnapshot.child("admin_phone").getValue().toString();
                            String admin_email=dataSnapshot.child("admin_email").getValue().toString();
                            String admin_website=dataSnapshot.child("admin_website").getValue().toString();
                            app_name_text.setText(app_name);
                            about_app_text.setText(about_app);
                            admin_name_text.setText(admin_name);
                            admin_email_text.setText(admin_email);
                            admin_website_text.setText(admin_website);
                            admin_phone_text.setText(admin_ph);
                        }catch (Exception e)
                        {
                            //Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
                    if(comment_key.equals("not"))
                    {
                        intent.putExtra("comment_key","not");
                    }
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
                if(comment_key.equals("not"))
                {
                    intent.putExtra("comment_key","not");
                }
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
                Intent intent=new Intent(HomeActivity.this,CategoryOfEntertainmentActivity.class);
                //intent.putExtra("details","health");
                intent.putExtra("key","visible_health");
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);


        MenuItem menuItem=menu.findItem(R.id.action_search);

        android.widget.SearchView searchView=(android.widget.SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //coordinatorLayout.setAnimation(AnimationUtils.loadAnimation(HomeActivity.this,R.anim.fade_transition_animation));
                scrollView_s.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                final ArrayList<PlaceDetails> newList = new ArrayList<>();
                for(PlaceDetails d : placeDetailsList){
                    if(d.getCity_name() != null && (d.getCity_name().toUpperCase().contains(newText) || d.getCity_name().toLowerCase().contains(newText.toLowerCase())))
                    {

                        newList.add(d);

                    }

                    //adapter
                    SearchAdapter searchAdapter = new SearchAdapter(HomeActivity.this,newList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    //cat1Rv.setLayoutManager(horizontalLayout1);
                    recyclerView.setAdapter(searchAdapter);



                    if(newText.isEmpty())
                    {
                        recyclerView.setVisibility(View.GONE);
                        scrollView_s.setVisibility(View.VISIBLE);
                        //startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    }
                }

                return true;
            }


        });



        super.onCreateOptionsMenu(menu);
        return true;
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
            intent.putExtra("test_key","nothing");
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
    //for menu

}
