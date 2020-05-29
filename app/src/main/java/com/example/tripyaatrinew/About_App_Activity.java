package com.example.tripyaatrinew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.AboutApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class About_App_Activity extends AppCompatActivity {

    private EditText app_name_text,about_app_text,admin_name_text,admin_phone_text,admin_email_text,admin_website_text;
    private Button save_button;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__app_);

        app_name_text=findViewById(R.id.app_name_id);
        about_app_text=findViewById(R.id.about_app_id);
        admin_name_text=findViewById(R.id.admin_name_id);
        admin_phone_text=findViewById(R.id.admin_phone_id);
        admin_email_text=findViewById(R.id.admin_email_id);
        admin_website_text=findViewById(R.id.admin_website_id);

        save_button=findViewById(R.id.admin_data_save_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("AboutApp");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String app_name=app_name_text.getText().toString();
                String about_app=about_app_text.getText().toString();
                String admin_name=admin_name_text.getText().toString();
                String admin_phone=admin_phone_text.getText().toString();
                String admin_email=admin_email_text.getText().toString();
                String admin_website=admin_website_text.getText().toString();

                AboutApp aboutApp=new AboutApp(app_name,about_app,admin_name,admin_phone,admin_email,admin_website);
                databaseReference.child(admin_phone).setValue(aboutApp);
                if (databaseReference != null) {
                    Toast.makeText(getApplicationContext(),"Data is stored successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
