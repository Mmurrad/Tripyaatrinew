package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tripyaatrinew.Adapter.GalleryCustomAdapter;
import com.example.tripyaatrinew.model.GalleryClass;
import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowGalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<GalleryClass> galleryClassList;
    GalleryCustomAdapter galleryCustomAdapter;
    String passkey,passvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gallery);


        recyclerView=findViewById(R.id.gallery_recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference("Gallery");

        Toolbar toolbar=findViewById(R.id.gallery_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final  Bundle bundle=getIntent().getExtras();
        passkey=bundle.getString("gallery_key");
        passvalue=bundle.getString("hedding_key");

        galleryClassList=new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                galleryClassList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    GalleryClass galleryClass=dataSnapshot1.getValue(GalleryClass.class);
                    if(passkey.equals(galleryClass.getPlace_name())&&passvalue.equals(galleryClass.getPlace_type()))
                    {
                        galleryClassList.add(galleryClass);
                    }

                }
                galleryCustomAdapter=new GalleryCustomAdapter(ShowGalleryActivity.this,galleryClassList);
                recyclerView.setAdapter(galleryCustomAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
