package com.example.tripyaatrinew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tripyaatrinew.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    DatabaseHelper helper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        arrayList=new ArrayList<>();
        listView=findViewById(R.id.history_listview_id);
        Toolbar toolbar=findViewById(R.id.history_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("History");


        helper=new DatabaseHelper(this);
        SQLiteDatabase hel = helper.getWritableDatabase();

       loadData();
    }

    private void loadData() {
        Cursor cursor=helper.showalldata();
        if(cursor==null)
        {
            Toast.makeText(getApplicationContext(),"History is empty",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext())
            {
                arrayList.add(cursor.getString(0));
                arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,arrayList);
                listView.setAdapter(arrayAdapter);
            }
        }
    }
    
}
