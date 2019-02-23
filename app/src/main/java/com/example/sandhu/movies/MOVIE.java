package com.example.sandhu.movies;

public class MOVIE {

    private String mIDMB;
    private String mName;
    private String mGENRE;
    private String mYEAR;
    private String mPoster;

    public String getmIDMB() {
        return mIDMB;
    }

    public String getmGENRE() {
        return mGENRE;
    }

    public String getmYEAR() {
        return mYEAR;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getmName() {
        return mName;
    }
public MOVIE(String name,String GENRE,String IDMB,String Duration,String Poster)
{
    mGENRE=GENRE;
    mYEAR=Duration;
    mIDMB=IDMB;
    mName=name;
    mPoster=Poster;
}
}

