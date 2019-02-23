package com.example.sandhu.movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Movie_Activity extends AppCompatActivity {
    private MOVIEAdapter mAdapter;
    private String COMP;


    private class Movie_AsyncTask extends AsyncTask<String, Void, List<MOVIE>> {

        @Override
        protected List<MOVIE> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<MOVIE> result = Movies_Utilites.fetchEarthquakeData(COMP);
            return result;
        }

        @Override
        protected void onPostExecute(List<MOVIE> data) {
            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
Intent startingIntent=getIntent();
COMP=startingIntent.getStringExtra("link");

        Log.e("MANI",COMP);
        setContentView(R.layout.new_activity);

        Movie_AsyncTask task = new Movie_AsyncTask();
       task.execute(COMP);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //List<MOVIE> movie = Movies_Utilites.fetchEarthquakeData("");




        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new MOVIEAdapter(this, new ArrayList<MOVIE>(){
        });

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);


        };












/*
        ArrayList<MOVIE> movie = Movies_Utilites.extractMovies();


        // Find a reference to the {@link ListView} in the layout
        ListView MovieListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        MOVIEAdapter adapter= new MOVIEAdapter(this,movie);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        MovieListView.setAdapter(adapter);
        */
    }

