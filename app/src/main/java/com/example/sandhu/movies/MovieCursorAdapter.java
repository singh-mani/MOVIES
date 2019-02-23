package com.example.sandhu.movies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MovieCursorAdapter extends CursorAdapter {

    public MovieCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.ID);
        TextView summary = (TextView) view.findViewById(R.id.KEY);
        // Extract properties from cursor
        int nam=cursor.getColumnIndex("ID");
        int breed=cursor.getColumnIndex("KEYY");
        String body = cursor.getString(nam);
        String Petbreed = cursor.getString(breed);
        // Populate fields with extracted properties
        name.setText(body);
        summary.setText(String.valueOf(Petbreed));
    }
}

