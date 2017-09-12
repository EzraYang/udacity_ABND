package com.example.android.poopcalendar.data;

import android.provider.BaseColumns;

/**
 * Created by EzraYang on 1/2/17.
 */

public class PoopContract {
    private PoopContract(){

    }

    public static abstract class PoopEntry implements BaseColumns{
        public static final String TABLE_NAME = "poop";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_POOP_TYPE = "type";
        public static final String COLUMN_POOP_NOTE = "note";


    }
}
