package com.example.android.travel_isfahan;

/**
 * Created by EzraYang on 2/6/17.
 */

public class Attraction {

    private int mName;
    private int mLocation;
    private int mImageId;

    public Attraction(int imageId, int nameId, int locId){
        mImageId = imageId;
        mName = nameId;
        mLocation = locId;
    }

    public int getmImageId() {
        return mImageId;
    }

    public int getmLocation() {
        return mLocation;
    }

    public int getmName() {
        return mName;
    }
}
