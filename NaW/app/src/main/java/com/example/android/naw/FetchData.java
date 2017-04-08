package com.example.android.naw;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Jeetesh on 4/4/2017.
 */

public class FetchData {

    private static final String LOG_TAG = FetchData.class.getSimpleName();


    private FetchData() {
    }

    public static Weather fetchweatherdata(String Url) {

        URL url = createUrl(Url);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in making the HTTP request.", e);
        }


        Weather weather = extractFeatureFromJson(jsonResponse);


        return weather;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                Log.v("GET RESPONSE IS CORRECT","SADASDSADASD");
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        Log.v("GOT INPUT STREAM","SADUSAHDIUHASIUDHUAGSUIDGUASGDIUGASGDASDA");
        return output.toString();
    }


    private static Weather extractFeatureFromJson(String weatherjson) {

        if (TextUtils.isEmpty(weatherjson)) {
            return null;
        }

        Weather weather = null ;
        String descripition = null;
        String icon = null ;


        try {


            JSONObject baseJsonResponse = new JSONObject(weatherjson);


            JSONArray weatherarray = baseJsonResponse.getJSONArray("weather");


            if (weatherarray.length() != 0)

            {
                JSONObject currentdata = weatherarray.getJSONObject(0);


                descripition = currentdata.getString("description");

                icon = currentdata.getString("icon");

            }

                JSONObject main = baseJsonResponse.getJSONObject("main");

                Double temp = Double.parseDouble(main.getString("temp"));

            Double temp_min = Double.parseDouble(main.getString("temp_min"));

            Double temp_max = Double.parseDouble(main.getString("temp_max"));

                int temp_int = (int)  (temp - 273.15);

                int temp_min_int= (int) (temp_min - 273.15);
            int temp_max_int= (int) (temp_max - 273.15);


            int Humidity = main.getInt("humidity");

            String city =  baseJsonResponse.getString("name");

                Log.v("THE VALUE OF DESCRIPTION STORED==========", descripition);

                 weather = new Weather(descripition, temp_int, Humidity , city , icon ,temp_min_int ,temp_max_int);



        }
        catch (JSONException e1) {
            Log.e("Cannot parse Jason", String.valueOf(e1));
        }

        Log.v("GOT THE JASON ", "FUCK");

        return weather;


        }




    }

