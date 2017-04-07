package com.example.android.naw;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jeetesh on 4/7/2017.
 */

public class NewsAdpater extends ArrayAdapter<News> {




        private int mcolour;
        public NewsAdpater(Activity context, ArrayList<News> news) {

            super(context,0,news);
            //mcolour=colour;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.newsview, parent, false);
            }

            // Get the {@link AndroidFlavor} object located at this position in the list
            News currentnews = getItem(position);


//            View container= listItemView.findViewById(R.id.container);
//
//            int color= ContextCompat.getColor(getContext(),mcolour);
//            container.setBackgroundColor(color);


            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView  title = (TextView) listItemView.findViewById(R.id.title);
            title.setText(currentnews.gettitle());


            TextView description = (TextView) listItemView.findViewById(R.id.description);
            description.setText(currentnews.getdescription());

        ImageView imageView=(ImageView) listItemView.findViewById(R.id.urlphoto);
           imageView.setImageBitmap(currentnews.getimage());
//
//            if(currentAndroidFlavor.has_image()) {
//
//                imageView.setImageResource(currentAndroidFlavor.getImage());
//                imageView.setVisibility(View.VISIBLE);
//            }
//            else {
//                imageView.setVisibility(View.GONE);
//            }
//            // Return the whole list item layout (containing 2 TextViews and an ImageView)
//            // so that it can be shown in the ListView
            return listItemView;
        }
    }

