package com.example.android.naw;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import static com.example.android.naw.R.id.tab;

public class MainActivity extends AppCompatActivity {

    private Toolbar mtoolbar;

    private ViewPager mviewpager;

    private TabLayout mtab ;
    private SimpleFragmentAdapter madapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);

        mtab = (TabLayout) findViewById(tab);

        mviewpager = (ViewPager) findViewById(R.id.pager);

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("News And Weather");

        madapter = new SimpleFragmentAdapter(getSupportFragmentManager());


        mtab.setupWithViewPager(mviewpager);

        mtab.addTab(mtab.newTab().setText("Weather"));

        mtab.addTab(mtab.newTab().setText("News"));


        mviewpager.setAdapter(madapter);


            mtab.getTabAt(0).setIcon(R.drawable.m01);
        mtab.getTabAt(1).setIcon(R.drawable.m02);










    }
}
