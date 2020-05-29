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
import android.widget.Toast;

import com.example.tripyaatrinew.Adapter.NewsCustomAdapter;
import com.example.tripyaatrinew.Database.DatabaseHelper;
import com.example.tripyaatrinew.model.NewsClass;
import com.example.tripyaatrinew.model.UploadDocument;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    List<NewsClass> newsClassList;
    private ListView newslist;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newslist=findViewById(R.id.news_listview_id);

        Toolbar toolbar=findViewById(R.id.news_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News");



        newsClassList=new ArrayList<>();



        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        databaseReference= FirebaseDatabase.getInstance().getReference("News");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsClassList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    try{
                        NewsClass newsClass=dataSnapshot1.getValue(NewsClass.class);
                        newsClassList.add(newsClass);
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                    }

                }
                final  NewsCustomAdapter newsCustomAdapter=new NewsCustomAdapter(NewsActivity.this, newsClassList);
                newslist.setAdapter(newsCustomAdapter);


                newslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //String click=newsCustomAdapter.getItem(i).getNews();
                        String click="www.prothomalo.com";
                        Toast.makeText(getApplicationContext(),""+click,Toast.LENGTH_SHORT).show();
                        databaseHelper.addData(click);
                        //Uri uri=Uri.parse(click);
                        ///Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        ///intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //startActivity(intent);
                        Intent intent=new Intent(NewsActivity.this,NewsViewActivity.class);
                        intent.putExtra("news",click);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
