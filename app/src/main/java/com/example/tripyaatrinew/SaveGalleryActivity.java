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

import com.example.tripyaatrinew.model.GalleryClass;
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
import com.squareup.picasso.Picasso;

public class SaveGalleryActivity extends AppCompatActivity {

    ImageView gallery_image;
    Button gallery_image_choose_button,gallery_image_save_button;
    Spinner spinner;
    EditText place_name_text;
    private Uri imageuri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask storageTask;
    ProgressBar progressBar;
    private static final int IMAGE_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_gallery);

        gallery_image=findViewById(R.id.gallery_imageview_id);
        gallery_image_choose_button=findViewById(R.id.gallery_choose_button_id);
        gallery_image_save_button=findViewById(R.id.gallery_save_button_id);
        spinner=findViewById(R.id.gallery_place_spinner_id);
        progressBar=findViewById(R.id.gallery_progress_id);
        place_name_text=findViewById(R.id.gallery_place_name_id);

        databaseReference= FirebaseDatabase.getInstance().getReference("Gallery");
        storageReference= FirebaseStorage.getInstance().getReference("Gallery");

        gallery_image_save_button.setOnClickListener(new View.OnClickListener() {
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
        gallery_image_choose_button.setOnClickListener(new View.OnClickListener() {
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
        final String place_hed=spinner.getSelectedItem().toString();
        final String place_name=place_name_text.getText().toString();

        StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageuri));

        reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Image is stored successfully",Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downloaduri=uriTask.getResult();
                progressBar.setVisibility(View.GONE);
                GalleryClass galleryClass=new GalleryClass(downloaduri.toString(),place_hed,place_name);
                databaseReference.push().setValue(galleryClass);
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
            Picasso.with(this).load(imageuri).into(gallery_image);
        }
    }

}
