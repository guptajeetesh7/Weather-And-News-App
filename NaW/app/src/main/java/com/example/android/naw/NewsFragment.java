package com.example.android.naw;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    String NewsApi ="https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=b601d28254034943a7928784f76f4c2c";
    View rootview;

    public NewsAdpater adapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview= inflater.inflate(R.layout.fragment_news2, container, false);

        ListView listView = (ListView) rootview.findViewById(R.id.list);

        adapter = new NewsAdpater(getActivity(),new ArrayList<News>() ) ;

//        LoaderManager loaderManager = getLoaderManager();
//
//
//        loaderManager.initLoader(0, null, this);

        listView.setAdapter(adapter);

        NewsTask Task = new NewsTask(getActivity());

        Task.execute(NewsApi);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News news =  adapter.getItem(position);

                Uri uri = Uri.parse(news.geturl());

                Intent intent = new Intent(Intent.ACTION_VIEW , uri);

                startActivity(intent);
            }
        });




        return rootview;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        //return new NewsLoader(NewsFragment.this, NewsApi);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }


    public class NewsLoader extends AsyncTaskLoader<List<News>> {

        /** Tag for log messages */
        private  final String LOG_TAG = NewsLoader.class.getName();

        /** Query URL */
        private String mUrl;

        /**
         *
         *
         * @param context of the activity
         * @param url to load data from
         */
        public NewsLoader(Context context, String url) {
            super(context);
            mUrl = url;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        /**
         * This is on a background thread.
         */
        @Override
        public List<News> loadInBackground() {
            if (mUrl == null) {
                return null;
            }

            // Perform the network request, parse the response, and extract a list of earthquakes.
            List<News> news = fetchnewsdata.fetchnews(mUrl);
            return news;
        }
    }


    private class NewsTask extends AsyncTask<String ,Void , List<News>> {

        private String murl ;

        public NewsTask(Context context ) {
            super();

        }


        @Override
        public List<News> doInBackground(String... urls){
            List<News> news;

            news= fetchnewsdata.fetchnews(urls[0]);

            return news ;


        }


        protected void onPostExecute(List<News> news) {

            ProgressBar bar = (ProgressBar) rootview.findViewById(R.id.bar);
            bar.setVisibility(View.GONE);

            adapter.addAll(news);
        }




    }


}
