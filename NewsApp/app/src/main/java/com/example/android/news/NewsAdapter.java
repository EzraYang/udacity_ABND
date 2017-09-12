package com.example.android.news;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EzraYang on 1/3/17.
 */

public class NewsAdapter extends ArrayAdapter {

    public NewsAdapter(Activity context, List<News> newsList){
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News currentNews = (News) getItem(position);
        TextView titleField = (TextView) convertView.findViewById(R.id.title_field);
        TextView sectionField = (TextView) convertView.findViewById(R.id.section_field);

        titleField.setText(currentNews.getmTitle());
        sectionField.setText(currentNews.getmSection());
        return convertView;

    }
}
