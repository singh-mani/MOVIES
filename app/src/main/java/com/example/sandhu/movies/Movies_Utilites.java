package com.example.sandhu.movies;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

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

import static com.example.sandhu.movies.MovieDbHelper.LOG_TAG;

public class Movies_Utilites {

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("MANI", "Problem building the URL ", e);
        }
        return url;
    }

    private static String jsonResponse = "";

    private static String makeHttpRequest(URL url) throws IOException {


        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("MANI", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("MANI", "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
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
        return output.toString();
    }


    private Movies_Utilites() {
    }

    private static List<MOVIE> extractFeatureFromJson(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }
       // public static ArrayList<MOVIE> extractMovies () {

            ArrayList<MOVIE> movies = new ArrayList<>();
            try {


                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
                String name = baseJsonResponse.getString("Title");
                String year = baseJsonResponse.getString("Year");
                String GENRE = baseJsonResponse.getString("Genre");
                String Ratings = baseJsonResponse.getString("imdbRating");
                String poster=baseJsonResponse.getString("imdbID");
                MOVIE mm = new MOVIE(name, GENRE, Ratings, year,poster);
                movies.add(mm);
                Log.e("MANI", "*************************  UTILITIES    *******************************************************************************************************************************************************************************************");

            } catch (JSONException e) {
                Log.e("MANI", "**********  ERROR  ******************************************************************************************************************************************************************************************************************", e);
            }
            return movies;
        }
    //}



    public static List<MOVIE> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<MOVIE> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }




}