package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.Lati_Longi_Class;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    Double latitude, longitude,s_latitude,s_longitude;
    Geocoder geocoder;
    List<Address> addresses;
    DatabaseReference databaseReference;
    String passkey;
    DatabaseHelper databaseHelper;
    TextView place_name,distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar=findViewById(R.id.maps_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Maps");
        geocoder = new Geocoder(this, Locale.ENGLISH);
        place_name=findViewById(R.id.map_place_name_id);
        distance=findViewById(R.id.map_distance_id);

        final  Bundle bundle=getIntent().getExtras();
        passkey=bundle.getString("map_key");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("Lati_longi");

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();



        locationCallback=new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if(locationResult!=null)
                {
                    for (Location location : locationResult.getLocations()) {
                        latitude= location.getLatitude();
                        longitude=location.getLongitude();
                        try {
                            addresses= geocoder.getFromLocation(latitude,longitude,1);
                            Address address=addresses.get(0);
                            String loca=address.getAddressLine(0)+"\t"+address.getPostalCode()+"\t"+address.getLocality()+"\t"+address.getCountryCode();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                                {
                                    Lati_Longi_Class lati_longi_class=dataSnapshot1.getValue(Lati_Longi_Class.class);
                                    if(passkey.equals(lati_longi_class.getPlace_name()))
                                    {
                                        place_name.setText(lati_longi_class.getPlace_name());
                                        try{
                                            float result[]=new float[10];
                                            float lati=Float.parseFloat(lati_longi_class.getLatitude());
                                            float longi=Float.parseFloat(lati_longi_class.getLongitude());
                                            Location.distanceBetween(lati,longi,latitude,longitude,result);

                                            distance.setText(""+result[0]/1000+" Km");
                                            databaseHelper.addData(""+result[0]/1000+" Km");
                                            //Toast.makeText(getApplicationContext(),"Distance = "+result[0]/1000,Toast.LENGTH_LONG).show();
                                        }catch (Exception e)
                                        {
                                            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        //Toast.makeText(MapsActivity.this,latitude+" "+longitude,Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        oncreateloaction();
    }

    private void oncreateloaction() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},101);
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }
}
