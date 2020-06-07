package com.example.tripyaatrinew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tripyaatrinew.model.Booking;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity {

    EditText name_text,email_text,phone_text,starting_text,placevisit_text,number0fperson_text;
    Button submit_button;
    TextView go_back_text;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        name_text=findViewById(R.id.user_name_id);
        email_text=findViewById(R.id.user_email_id);
        phone_text=findViewById(R.id.user_phone_id);
        starting_text=findViewById(R.id.user_starting_location_id);
        placevisit_text=findViewById(R.id.user_location_of_visit_id);
        number0fperson_text=findViewById(R.id.user_number_of_person_id);
        submit_button=findViewById(R.id.submit_booking);
        go_back_text=findViewById(R.id.go_back_id);
        progressBar=findViewById(R.id.user_progress_bar_id);
        datePicker=findViewById(R.id.date_picker_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("Booking");



        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name_text.getText().toString();
                String email=email_text.getText().toString();
                String phone=phone_text.getText().toString();
                String starting=starting_text.getText().toString();
                String place=placevisit_text.getText().toString();
                String person=number0fperson_text.getText().toString();

                if(name.isEmpty())
                {
                    name_text.setError("Enter Name");
                    name_text.requestFocus();
                    return;
                }
                if(phone.isEmpty())
                {
                    phone_text.setError("Enter Phone");
                    phone_text.requestFocus();
                    return;
                }if(email.isEmpty())
                {
                    email_text.setError("Enter Email");
                    email_text.requestFocus();
                    return;
                }if(starting.isEmpty())
                {
                    starting_text.setError("Enter Starting Location");
                    starting_text.requestFocus();
                    return;
                }if(place.isEmpty())
                {
                    placevisit_text.setError("Enter Location");
                    placevisit_text.requestFocus();
                    return;
                }
                if(person.isEmpty())
                {
                    number0fperson_text.setError("Enter Number");
                    number0fperson_text.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);

                    Booking booking=new Booking(name,email,phone,starting,place,person);
                    databaseReference.push().setValue(booking);

                    if(databaseReference!=null)
                    {
                        progressBar.setVisibility(View.GONE);
                        Intent intent=new Intent(BookingActivity.this,HomeActivity.class);
                        intent.putExtra("admin_key","");
                        intent.putExtra("count","");
                        startActivity(intent);
                    }
                }
            }
        });
        go_back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookingActivity.this,HomeActivity.class);
                intent.putExtra("admin_key","");
                intent.putExtra("count","");
                startActivity(intent);
            }
        });

    }
}
