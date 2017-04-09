package com.example.android.naw;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Weatherfragment extends Fragment  {

     String city;
    Button button;
    View rootview;
    EditText getcity;
    ProgressBar bar;

    public Weatherfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview = inflater.inflate(R.layout.fragment_weatherfragment, container, false);

       getcity =(EditText)  rootview.findViewById(R.id.cityname);

         bar = (ProgressBar) rootview.findViewById(R.id.bar);


        String WeatherApi = "http://api.openweathermap.org/data/2.5/weather?q=kota&appid=c242d2031036ff05b3bd8559f91e2449";

        WeatherTask  task = new WeatherTask(getActivity());

        task.execute(WeatherApi);

        button =(Button) rootview.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
              city= getcity.getText().toString();

                bar.setVisibility(View.VISIBLE);
                String WeatherAp = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=c242d2031036ff05b3bd8559f91e2449";

                WeatherTask  task = new WeatherTask(getActivity());

                task.execute(WeatherAp);}


            });






        /*
 * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
 * performs a swipe-to-refresh gesture.
 */





        return rootview;
    }




    private class WeatherTask extends AsyncTask<String ,Void , Weather> {

    private String murl ;

    public WeatherTask(Context context ) {
        super();

    }


    @Override
   public Weather doInBackground(String... urls){
        Weather weather ;

        weather = FetchData.fetchweatherdata(urls[0]);

        return weather ;


    }


    protected void onPostExecute(Weather weather) {
        bar.setVisibility(View.GONE);
       update(weather);
    }




  }
    public void update(Weather weather) {


        String icon = weather.geticon();
        int id = getIcon(icon);
        TextView description = (TextView) rootview.findViewById(R.id.description);
        description.setText(weather.getDescription());

        Log.v("SUCESSSSSSSSSSSSFULLLLLLLLLLLLLLL" ,"GET THE DEScr");

        TextView temp= (TextView) rootview.findViewById(R.id.tempavg);
        temp.setText(weather.getTemp());

        TextView humidity = (TextView) rootview.findViewById(R.id.humidity);
        humidity.setText(weather.getHumidity());

        TextView city = (TextView) rootview.findViewById(R.id.place);
        city.setText(weather.getname());

        ImageView imageView =(ImageView)  rootview.findViewById(R.id.imageView);
        imageView.setImageResource(id);




    }

    public int getIcon(String icon) {
        if (icon.equals("01d"))
        {
            return R.drawable.d01;
        }
        if (icon.equals("02d"))
        {
            return R.drawable.d02;
        }
        if (icon.equals("01n"))
        {
            return R.drawable.n01;
        }
        if (icon.equals("02n"))
        {
            return R.drawable.n01;
        }
        if (icon.equals("03d"))
        {
            return R.drawable.d03;
        }

        if (icon.equals("03n"))
        {
            return R.drawable.n04;
        }
        if (icon.equals("04n"))
        {
            return R.drawable.n04;
        }

        if (icon.equals("04d"))
        {
            return R.drawable.n04;
        }

        if (icon.equals("09d"))
        {
            return R.drawable.d09;
        }


        if (icon.equals("09n"))
        {
            return R.drawable.n09;
        }

        if (icon.equals("11n"))
        {
            return R.drawable.t11;
        }
        if (icon.equals("11d"))
        {
            return R.drawable.t11;
        }
        if (icon.equals("10d"))
        {
            return R.drawable.n10;
        }
        if (icon.equals("10n"))
        {
            return R.drawable.n10;
        }
        if (icon.equals("50n"))
        {
            return R.drawable.n50;
        }
        if (icon.equals("50d"))
        {
            return R.drawable.d02;
        }




        return R.drawable.image;
    }
}




