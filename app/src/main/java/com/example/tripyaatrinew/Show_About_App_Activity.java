package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.AboutApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Show_About_App_Activity extends AppCompatActivity {

    TextView app_name_text,about_app_text,admin_name_text,admin_phone_text,admin_email_text,admin_website_text;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__about__app_);

        app_name_text=findViewById(R.id.get_app_name_id);
        about_app_text=findViewById(R.id.get_about_app_id);
        admin_name_text=findViewById(R.id.get_admim_name_id);
        admin_phone_text=findViewById(R.id.get_admim_phone_id);
        admin_email_text=findViewById(R.id.get_admim_email_id);
        admin_website_text=findViewById(R.id.get_admim_website_id);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("AboutApp").child("01521307785");

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        databaseHelper.addData("AboutAppAndAdmin");

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
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
