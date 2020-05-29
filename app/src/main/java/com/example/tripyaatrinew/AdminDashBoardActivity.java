package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripyaatrinew.model.VisitorsCount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDashBoardActivity extends AppCompatActivity {

    CardView visitors_card,details_card,document_card,settings_card,about_app_card,news_add_card,photo_library,map_card;
    DatabaseReference databaseReference;
    int result=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        visitors_card=findViewById(R.id.total_visit_id);
        details_card=findViewById(R.id.add_details_id);
        document_card=findViewById(R.id.add_dccuments_id);
        settings_card=findViewById(R.id.admin_settings_id);
        about_app_card=findViewById(R.id.about_app_id);
        news_add_card=findViewById(R.id.add_news_id);
        photo_library=findViewById(R.id.gallery_image_id);
        map_card=findViewById(R.id.map_image_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("Count");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    VisitorsCount visitorsCount=dataSnapshot1.getValue(VisitorsCount.class);
                    result=result+visitorsCount.getCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        details_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,AddDetailsActivity.class);
                startActivity(intent);
            }
        });

        document_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,UploadDocumentActivity.class);
                startActivity(intent);
            }
        });
        about_app_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,About_App_Activity.class);
                startActivity(intent);
            }
        });
        news_add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,Add_News_Activity.class);
                startActivity(intent);
            }
        });
        photo_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,SaveGalleryActivity.class);
                startActivity(intent);
            }
        });
        map_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,Set_Leti_longiActivity.class);
                startActivity(intent);
            }
        });
        visitors_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Number Of Visitors "+result,Toast.LENGTH_LONG).show();
            }
        });
    }
}
