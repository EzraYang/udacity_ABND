package com.example.android.booklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EzraYang on 12/21/16.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleField = (TextView) convertView.findViewById(R.id.title_field);
        TextView subtitleField = (TextView) convertView.findViewById(R.id.subtitle_field);
        TextView authorField = (TextView) convertView.findViewById(R.id.author_field);

        titleField.setText(currentBook.getmTitle());
        subtitleField.setText(currentBook.getmSubtitle());
        authorField.setText(currentBook.getmAuthor());

        return convertView;
    }
}
