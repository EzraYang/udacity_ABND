package com.example.android.poopcalendar.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by EzraYang on 1/2/17.
 */

public class PoopDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "poopcalendar.db";

    public static final String LOG_TAG = PoopDbHelper.class.getSimpleName();

    public PoopDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " +
                PoopContract.PoopEntry.TABLE_NAME + " (" +
                PoopContract.PoopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PoopContract.PoopEntry.COLUMN_POOP_TYPE + " INTEGER NOT NULL, " +
                PoopContract.PoopEntry.COLUMN_POOP_NOTE + " TEXT)";
        Log.i(LOG_TAG, "SQL_CREATE_ENTRIES is" + SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
