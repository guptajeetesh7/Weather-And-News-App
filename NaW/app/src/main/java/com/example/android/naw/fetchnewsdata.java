package com.example.android.naw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeetesh on 4/7/2017.
 */

public class fetchnewsdata { private static final String LOG_TAG = FetchData.class.getSimpleName();


    private fetchnewsdata() {
    }

    public static List<News> fetchnews(String Url) {

        URL url = createUrl(Url);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in making the HTTP request.", e);
        }


       List<News> news = extractFeatureFromJson(jsonResponse);


        return news;
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


    private static List<News> extractFeatureFromJson(String weatherjson) {

        if (TextUtils.isEmpty(weatherjson)) {
            return null;
        }

      List<News> news = new ArrayList<News>();
        News newS=null;
        String title = null;
        String description = null ;
        String date = null;


        try {


            JSONObject baseJsonResponse = new JSONObject(weatherjson);


            JSONArray articles = baseJsonResponse.getJSONArray("articles");


            for(int i = 0 ; i < articles.length() ; i ++)

            {
                JSONObject currentdata = articles.getJSONObject(i);


                title = currentdata.getString("title");

                description= currentdata.getString("description");

                String url = currentdata.getString("url");

                String urlimage = currentdata.getString("urlToImage");

                URL urll = new URL(urlimage);
                Bitmap bmp = BitmapFactory.decodeStream(urll.openConnection().getInputStream());

                newS = new News(title,description,url ,bmp);

                news.add(newS);

            }








        }
        catch (JSONException e1) {
            Log.e("Cannot parse Jason", String.valueOf(e1));
        }
        catch (MalformedURLException e) {
            Log.v("Cannot parson" , "Image url");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.v("GOT THE JASON ", "FUCK");

        return news;

    }
}








