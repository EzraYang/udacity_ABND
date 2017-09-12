package com.example.android.poopcalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.poopcalendar.data.PoopContract;
import com.example.android.poopcalendar.data.PoopDbHelper;

public class MainActivity extends AppCompatActivity {

    private PoopDbHelper mDbHelper;
    private SQLiteDatabase mReadableDb;
    private SQLiteDatabase mWritableDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new PoopDbHelper(this);
        mReadableDb = mDbHelper.getReadableDatabase();
        mWritableDb = mDbHelper.getWritableDatabase();
    }

    private void addPoop(int type, String note){

        ContentValues value = new ContentValues();
        value.put(PoopContract.PoopEntry.COLUMN_POOP_TYPE, type);
        value.put(PoopContract.PoopEntry.COLUMN_POOP_NOTE, note);

        long newRowId = mWritableDb.insert(PoopContract.PoopEntry.TABLE_NAME, null, value);
        Log.i("MainActivity", "New row id is " + newRowId);

    }

    private Cursor readPoop(){

        Cursor cursor = mReadableDb.query(PoopContract.PoopEntry.TABLE_NAME, null, null, null, null, null, null);
        cursor.close();
        return cursor;
    }

}
