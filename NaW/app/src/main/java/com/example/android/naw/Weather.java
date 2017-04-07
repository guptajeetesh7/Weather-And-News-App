package com.example.android.naw;

import android.util.Log;

/**
 * Created by Jeetesh on 4/4/2017.
 */

public class Weather {

    private String mdescription ;

    private int  mtemp ;

    private int mHumidity;

    private String mname;

    private String micon;

    public  Weather (String description , int temp , int Humidity , String name , String icon )
    {
        mdescription = description ;
        mtemp = temp ;
        mHumidity = Humidity;
     mname= name ;
        micon =icon;
    }

    public  String getDescription()

    {
        Log.e("GET THE DDDDDDDDDDDDDDDEEEEEEEESSSSSSSSSSS", mdescription);
        return mdescription ;



    }

    public  String getTemp()
    {
        String temp =String.valueOf(mtemp) ;
        return temp;

    }

    public  String getHumidity()
    {
        String temp =String.valueOf(mHumidity) ;
        return temp;

    }

    public String getname()
    {return  mname;}





    public  String geticon()
    {
        return micon;
    }




}
