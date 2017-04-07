package com.example.android.naw;

import android.graphics.Bitmap;

/**
 * Created by Jeetesh on 4/7/2017.
 */

public class News {


    private String mtitle;
    private String mdescription;
    private String murl;
    private Bitmap mbmp;

    public News(String title , String description , String url ,Bitmap bmp)
    {
        mtitle=title;
        mdescription=description;
        murl=url;
        mbmp=bmp;
    }

    public String gettitle(){
        return mtitle;
    }

    public  String getdescription()
    {
        return  mdescription;
    }

    public String geturl()
    {
        return  murl ;
    }

    public  Bitmap getimage()
    {
        return mbmp;
    }
}
