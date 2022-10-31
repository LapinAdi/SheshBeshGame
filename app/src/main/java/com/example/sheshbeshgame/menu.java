package com.example.sheshbeshgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPlay = findViewById(R.id.buttonPlayMenu);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnPlay.getId()) {     //   מעביר מעמוד תפריט לעמוד הוספת שחקן
            Intent intent = new Intent(this, AddPlayer.class);

            startActivity(intent);
        }
    }
}