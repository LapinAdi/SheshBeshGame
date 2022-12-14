package com.example.sheshbeshgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //  add player שלב א הגדרת רכיבים
    private String name1,
            name2;

    private TextView tvName1, tvName2;
    private Button btnUserBack;

    private LinearLayout verticalLayout1, verticalLayout2;
    private final int[] stoneArragnement = new int[]{5, 1, 1, 1, -3, 1, -5, 1, -4 , 1, -1, 2, -5, 1, 1, 1, 3, -1, 5, -1, -1, -1, -1, -2};
    private final int[] margin = new int[]{0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0};
    private Block[] blocks;


    final int LEN = 24;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

        GameManager gameManager = new GameManager();
        //

        // שלב ב קישור רכיבים ADD user
        tvName1 = (TextView) findViewById(R.id.tvName1);
        tvName2 = (TextView) findViewById(R.id.tvName2);

        verticalLayout1 = findViewById(R.id.verticalLayout1);
        verticalLayout2 = findViewById(R.id.verticalLayout2);

        blocks = new Block[LEN];


        // ----------------------------------------------
        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");

        }
        tvName1.setText(name1);
        tvName2.setText(name2);
        //---------------------------------------

        blocks = createBlockStone(blocks);

    }

    public Block[] createBlockStone(Block[] blocks) {
        //
        //

        for (int i = 0; i < this.LEN; i++) {
            LinearLayout correctLayout = findLayout(i);
            blocks[i] = new Block(correctLayout.getContext(), Math.abs(stoneArragnement[i]),
                    stoneArragnement[i] > 0 ? 'w' : 'b');


            if (i > 11) {
//

                blocks[i].setGravity(Gravity.RIGHT);

                if (i == 12) {
                    doMargtopScreen(i);
                } else {
                    doMargBottomUsual(i);
                }

            } else {
                doMargBottomUsual(i);
                if (i == 0) {
                    doMargtopScreen(i);
                }

            }


            if (i == 5 || i == 17) {
              doMargtopMiddleScreen(i);
            }



            correctLayout.addView(blocks[i]);
            if (i == 11) reverse11();


        }

        return blocks;
    }

    private void doMargBottomUsual(int i) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 50);
        blocks[i].setLayoutParams(lp);

    }

    private void doMargtopScreen(int i) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 70, 0, 50);
        blocks[i].setLayoutParams(lp);

    }
    private void doMargtopMiddleScreen(int i) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 170);
        blocks[i].setLayoutParams(lp);

    }


    private LinearLayout findLayout(int row) {
        return row < 12 ? verticalLayout1 : verticalLayout2;
    }

    private int dpToPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getApplicationContext().getResources().getDisplayMetrics());
    }

    private void reverse11() {
        Block temp;
        for (int i = 0; i < 11; i++) {
            temp = blocks[i];
            blocks[i] = blocks[11 - i];
            blocks[11 - i] = temp;
        }
    }
}