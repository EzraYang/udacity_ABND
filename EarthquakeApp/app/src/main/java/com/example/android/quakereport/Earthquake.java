package com.example.android.quakereport;

/**
 * Created by EzraYang on 12/8/16.
 */

public class Earthquake {
    private double mMagnitude;
    private String mPlace;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String place, long date, String url){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeInMilliseconds = date;
        mUrl = url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

}
