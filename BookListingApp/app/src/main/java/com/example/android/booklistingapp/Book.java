package com.example.android.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EzraYang on 12/21/16.
 */

public class Book implements Parcelable {

    String mTitle;
    String mSubtitle;
    String mAuthor;

    // a public constructor
    public Book(String title, String subtitle, String author){
        mTitle = title;
        mSubtitle = subtitle;
        mAuthor = author;
    }

    // constructor with parcel
    private Book(Parcel parcel){
        mTitle = parcel.readString();
        mSubtitle = parcel.readString();
        mAuthor = parcel.readString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mSubtitle);
        parcel.writeString(mAuthor);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
}
