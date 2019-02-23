package com.example.sandhu.movies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

private String MY_URL="http://www.omdbapi.com/?apikey=67bd26f&t=";
private String IMG_URL="http://img.omdbapi.com/?apikey=67bd26f&i=";
private void open_Movie_Activity(String complete,String img)
{
    Intent intent=new Intent(this,Movie_Activity.class);
    intent.putExtra("link",complete);
    intent.putExtra("poster",img);
    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=findViewById(R.id.EDIT);
               String movie=editText.getText().toString();
String complete=MY_URL+movie;
String poster=IMG_URL;


               if(movie.isEmpty()){
                   TextView RESULT=findViewById(R.id.RESULT);
                    RESULT.setText("PLEASE ENTER ANY MOVIE\n");
                  Toast tt= Toast.makeText(getApplicationContext()," MARNA HAI TERE KO   -   DAR NHI LAGTA KYA??",Toast.LENGTH_SHORT);
                   tt.show();
               }
               else{


                    open_Movie_Activity(complete,poster);

               }
            }
        });
    }





}
