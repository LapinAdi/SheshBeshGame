package com.example.sheshbeshgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    //  add player שלב א הגדרת רכיבים
    private String name1,
            name2;

    private TextView tvName1, tvName2;
    private Button btnUserBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // שלב ב קישור רכיבים ADD user
        tvName1 = (TextView) findViewById(R.id.tvName1);
        tvName2 = (TextView) findViewById(R.id.tvName2);

        // ----------------------------------------------
        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");

        }
        tvName1.setText(name1);
        tvName2.setText(name2);
        //----------------------------------------


    }



}