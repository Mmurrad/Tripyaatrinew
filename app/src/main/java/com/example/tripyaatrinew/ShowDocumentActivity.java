package com.example.tripyaatrinew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.UploadDocument;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDocumentActivity extends AppCompatActivity {
    List<UploadDocument> uploadDocumentList;
    private ListView listView;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document);
        Toolbar toolbar=findViewById(R.id.news_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Document");

        listView=findViewById(R.id.document_listview_id);
        uploadDocumentList=new ArrayList<>();

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        Viewallfiles();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UploadDocument uploadPdf=uploadDocumentList.get(i);
                Intent intent=new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uploadPdf.getPdf()));
                startActivity(intent);
            }
        });

    }
    private void Viewallfiles() {

        databaseReference= FirebaseDatabase.getInstance().getReference("Uploads_pdf");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadDocumentList.clear();
                for(DataSnapshot postsnapsot : dataSnapshot.getChildren())
                {

                    UploadDocument uploadPdf=postsnapsot.getValue(UploadDocument.class);
                    uploadDocumentList.add(uploadPdf);
                }
                String[] uploads=new String[uploadDocumentList.size()];
                for(int i=0;i<uploads.length;i++)
                {
                    uploads[i]=uploadDocumentList.get(i).getPdf();
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.sample_of_document,R.id.document_text_id,uploads);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
