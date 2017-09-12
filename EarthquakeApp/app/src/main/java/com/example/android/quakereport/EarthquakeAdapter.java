package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by EzraYang on 12/8/16.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{


    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        double magnitude;
        long timeInMs;
        String[] locationArray;

        // Check if the existing view is being reused, otherwise inflate the view
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // get current item of the earthquake ArrayList
        final Earthquake currentEarthquake = getItem(position);

        /**
         * deal with magnitude TextView and its background
         */
        // stuff mMagnitude of currentEarthquake into txt_magnitude TextView
        magnitude = currentEarthquake.getmMagnitude();
        // using helper function formatMagnitude to convert the double object into specified format
        // fill txt_magnitude TextView with formattedMag
        TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.txt_magnitude);
        magnitudeTextView.setText(formatMagnitude(magnitude));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitude);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        /**
         * deal with two location TextViews
         */
        // find the two TextView in charge of displaying location
        TextView locationPreciseTextView = (TextView) convertView.findViewById(R.id.txt_location_precise);
        TextView locationCityTextView = (TextView) convertView.findViewById(R.id.txt_location_city);

        String primaryLocation = currentEarthquake.getmPlace();
        // if primaryLocation contains a precise loc, split the primaryLocation into two parts
        // and fill corresponding TextView
        if ( primaryLocation.contains(",")){
            locationArray = primaryLocation.split(", ");
            locationPreciseTextView.setText(locationArray[0]);
            locationCityTextView.setText(locationArray[1]);
        }
        // else, the primaryLocation does not near any city,
        // just set the text to be "near the" xx location
        else{
            locationPreciseTextView.setText("Near the");
            locationCityTextView.setText(primaryLocation);
        }

        /**
         * deal with date and time TextViews
         */
        // get mDate of currentEarthquake, and format it using helper function, and stuff them into
        // corresponding TextView
        TextView dateTextView = (TextView) convertView.findViewById(R.id.txt_date);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.txt_time);
        timeInMs = currentEarthquake.getmTimeInMilliseconds();
        // re-formatting time to be a Date object
        Date timeDate = new Date(timeInMs);

        String formattedDate = formatDate(timeDate);
        String formattedTime = formatTime(timeDate);

        dateTextView.setText(formattedDate);
        timeTextView.setText(formattedTime);

//        /**
//         * set OnClickListener to browse internet on every convertview
//         */
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browseInternet = new Intent(Intent.ACTION_VIEW, Uri.parse(currentEarthquake.getmUrl()));
//                startActivity(browseInternet);
//            }
//        });

        return convertView;
    }



    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     *
     */
    private int getMagnitudeColor(double magnitude){

        DecimalFormat magnitudeOneDigitFormatter = new DecimalFormat("0");
        String magnitudeOneDigit = magnitudeOneDigitFormatter.format(magnitude);
        int colorId;

        switch (magnitudeOneDigit){
            case "10":
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
            case "9":
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            case "8":
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case "7":
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case "6":
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case "5":
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case "4":
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case "3":
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case "2":
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
        }
    }
}
