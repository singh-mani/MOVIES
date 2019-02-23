package com.example.sandhu.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class MOVIEAdapter extends ArrayAdapter<MOVIE> {
    private String IMG;
    private class DownloadImage extends AsyncTask<String,Void,Bitmap>
    {ImageView bmImage;
        public DownloadImage(ImageView bmImage) {
            this.bmImage=bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("MANI", "NO IMAGE FOUNDD DD ");

            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



    private int getMagnitudeColor(String mag) {
        double magnitude=Double.parseDouble(mag);
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);


        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    public MOVIEAdapter(@NonNull Context context, List<MOVIE> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView ==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        Log.v("MANI", "****************  ADAPTER   ********************************************************************************************************************************************************************************************************");


        MOVIE curr_movie=getItem(position);
        TextView imdb=listItemView.findViewById(R.id.IMDB);
        imdb.setText(curr_movie.getmIDMB());

            TextView NAME=listItemView.findViewById(R.id.NAME);
            NAME.setText(curr_movie.getmName());

            TextView YEAR=listItemView.findViewById(R.id.YEAR);
            YEAR.setText(curr_movie.getmYEAR());

            TextView GENRE=listItemView.findViewById(R.id.GENRE);
            GENRE.setText(curr_movie.getmGENRE());

        new DownloadImage((ImageView) listItemView.findViewById(R.id.img)).execute("http://img.omdbapi.com/?apikey=67bd26f&i="+curr_movie.getPoster());



        GradientDrawable magnitude_circle=(GradientDrawable)imdb.getBackground();
        int magnitude_color=getMagnitudeColor(curr_movie.getmIDMB());
        magnitude_circle.setColor(magnitude_color);

    return listItemView;
    }
}
