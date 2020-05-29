package com.example.tripyaatrinew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.os.Build.VERSION_CODES.N;
import static java.sql.Types.VARCHAR;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="database.db";
    public static final String TABLE_NAME="History_table";
    public static final String NAME="Name";
    public Context context;
    //private static final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"("+NAME+"VARCHAR(20));";

    public static final String CREATE_TABLE = "create table "+ TABLE_NAME +" (" +
            NAME + " TEXT " +
            ")";
    private SQLiteDatabase sqLiteDatabase;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void open()
    {
        sqLiteDatabase = getWritableDatabase();

    }


    public boolean addData(String name)
    {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);

        Long insertedRow = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        this.close();

        if(insertedRow>0)
        {
            return true;
        }
        else return false;
    }

    public Cursor showalldata()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return  cursor;
    }
}
