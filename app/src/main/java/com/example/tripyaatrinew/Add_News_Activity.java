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
import android.widget.Toast;

import com.example.tripyaatrinew.model.NewsClass;
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

public class Add_News_Activity extends AppCompatActivity {

    private EditText news_link,news_name_text,description_text;
    private Button save_news,choose_image;
    private ImageView imageView;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask storageTask;
    private Uri imageuri;
    private static final int IMAGE_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__news_);

        news_link=findViewById(R.id.enter_news_id);
        news_name_text=findViewById(R.id.enter_news_name_id);
        save_news=findViewById(R.id.news_link_save_id);
        description_text=findViewById(R.id.enter_news_des_id);
        choose_image=findViewById(R.id.news_picture_choose_id);
        imageView=findViewById(R.id.news_image_id);
        progressBar=findViewById(R.id.news_progressbar_id);


        databaseReference= FirebaseDatabase.getInstance().getReference("News");
        storageReference= FirebaseStorage.getInstance().getReference("News");

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opnenfilechooser();
            }
        });

        save_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String news=news_link.getText().toString();
                final String news_name=news_name_text.getText().toString();
                final String description=description_text.getText().toString();


                if(news.isEmpty())
                {
                    news_link.setError("Enter News Link");
                    news_link.requestFocus();
                    return;
                }
                if(news_name.isEmpty())
                {
                    news_name_text.setError("Enter News Link");
                    news_name_text.requestFocus();
                    return;
                }
                if(description.isEmpty())
                {
                    description_text.setError("Enter News Link");
                    description_text.requestFocus();
                    return;
                }
                if(news_name.isEmpty())
                {
                    news_name_text.setError("Enter News Link");
                    news_name_text.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageuri));
                    reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(),"Image is stored successfully",Toast.LENGTH_SHORT).show();
                            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());
                            Uri downloaduri=uriTask.getResult();
                            progressBar.setVisibility(View.GONE);
                            NewsClass newsClass=new NewsClass(news,news_name,description,downloaduri.toString());
                            databaseReference.child(newsClass.getName()).setValue(newsClass);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Image is not stored successfully",Toast.LENGTH_SHORT).show();
                        }
                    });


                    if(databaseReference!=null)
                    {
                        progressBar.setVisibility(View.GONE);
                        news_name_text.setText("");
                        news_link.setText("");
                        description_text.setText("");
                    }
                }
            }
        });
    }

    public String getFileExtention(Uri imageuri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
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
