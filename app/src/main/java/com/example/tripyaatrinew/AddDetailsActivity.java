package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tripyaatrinew.model.PlaceDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.manojbhadane.QButton;
import com.squareup.picasso.Picasso;

public class AddDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    Button selectimage_button,save_button;
    EditText state_name_text,city_name_text,place_name_id,address_text,phone_text,web_link_text,about_place_text,area_text,doctor_name;
    EditText charges,specialist,graduation;
    private Uri imageuri;
    Spinner spinner,sub_spinner;
    DatabaseReference databaseReference,statedatabase,citydatabase,doctordatabase;
    StorageReference storageReference;
    StorageTask storageTask;
    ProgressBar progressBar;

    private static final int IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        imageView=findViewById(R.id.imageview_id);
        selectimage_button=findViewById(R.id.choose_button_id);
        save_button=findViewById(R.id.save_button_id);

        doctor_name=findViewById(R.id.doctor_name_id);
        charges=findViewById(R.id.doctor_charges_id);
        specialist=findViewById(R.id.doctor_specialist_id);
        graduation=findViewById(R.id.doctor_graduation_id);
        state_name_text=findViewById(R.id.state_name_id);
        area_text=findViewById(R.id.area_id);
        city_name_text=findViewById(R.id.city_name_id);
        place_name_id=findViewById(R.id.place_name_id);
        address_text=findViewById(R.id.address_id);
        phone_text=findViewById(R.id.phone_id);
        web_link_text=findViewById(R.id.web_id);
        about_place_text=findViewById(R.id.about_place_id);
        spinner=findViewById(R.id.place_spinner_id);
        sub_spinner=findViewById(R.id.place_sub_spinner_id);
        progressBar=findViewById(R.id.progress_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("Details");
        statedatabase=FirebaseDatabase.getInstance().getReference("StateList");
        citydatabase=FirebaseDatabase.getInstance().getReference("CityList");
        doctordatabase=FirebaseDatabase.getInstance().getReference("DoctorDetails");
        storageReference= FirebaseStorage.getInstance().getReference("Details");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(storageTask!=null && storageTask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Uploading in progress",Toast.LENGTH_SHORT).show();
                }
                else {
                    savedata();
                }

            }
        });

        selectimage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opnenfilechooser();
            }
        });

    }


    public String getFileExtention(Uri imageuri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }
    private void savedata() {
        progressBar.setVisibility(View.VISIBLE);
        final String state_name=state_name_text.getText().toString();
        final String city_name=city_name_text.getText().toString();
        final String place_name=place_name_id.getText().toString();
        final String address=address_text.getText().toString();
        final String phone=phone_text.getText().toString();
        final String web=web_link_text.getText().toString();
        final String area=area_text.getText().toString();
        final String about_place=about_place_text.getText().toString();
        final String place_hed=spinner.getSelectedItem().toString();
        final String place_sub_hed=sub_spinner.getSelectedItem().toString();

        final String d_name=doctor_name.getText().toString();
        final String d_charge=charges.getText().toString();
        final String d_specialis=specialist.getText().toString();
        final String d_graduation=graduation.getText().toString();


        StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageuri));

        reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image is stored successfully",Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downloaduri=uriTask.getResult();
                progressBar.setVisibility(View.GONE);

                if(place_hed.equals("Health")&&place_sub_hed.equals("Doctor Clinic"))
                {
                    PlaceDetails placeDetails=new PlaceDetails(state_name,city_name,address,phone,web,downloaduri.toString(),place_hed,place_sub_hed,d_name,d_specialis,d_charge,d_graduation);
                    doctordatabase.child(phone).setValue(placeDetails);
                }
                else {
                    PlaceDetails placeDetails1=new PlaceDetails(state_name,area);
                    PlaceDetails placeDetails2=new PlaceDetails(city_name,state_name,address);
                    PlaceDetails placeDetails=new PlaceDetails(state_name,city_name,place_name,address,phone,web,area,about_place,downloaduri.toString(),place_hed,place_sub_hed);

                    databaseReference.child(phone).setValue(placeDetails);
                    statedatabase.child(state_name).setValue(placeDetails1);
                    citydatabase.child(city_name).setValue(placeDetails2);
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Image is not stored successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void opnenfilechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null &&data.getData()!=null)
        {
            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(imageView);
        }
    }
}
