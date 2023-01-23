package com.example.sheshbeshgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardDesign, View.OnClickListener {

    //  add player שלב א הגדרת רכיבים
    private String name1,
            name2;

    private TextView tvName1, tvName2;
    private Button btnUserBack;

    private LinearLayout verticalLayout1, verticalLayout2;
    private final int[] stoneArragnement = new int[]{5, 0, 0, 0, -3, 0, -5, 0, 0 , 0, 0, 2, -5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, -2};
   //private final int[] stoneArragnement = new int[]{2, 1, 1, 1, 1, -5, 0, -3, -1 , -1, -1, 5, -5, -1, -1, -1, 3, 1, 5, 1, 1, 1, 1, -2};
    private final int[] margin = new int[]{0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0};
    private Block[] blocks;

    private int check,from,to;

    final int LEN = 24;
    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

        gameManager = new GameManager();
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

        createBlockStone(blocks);
   //     setOrderID(blocks,LEN);
        pirntBlockId(blocks,LEN);


    }

    @Override
    public void onClick(View view) {


        if(gameManager.isGameGoing()){

            Log.d("Shesh", " tuched : id "+ view.getId()  );

            if(check<2){
                 int touchID = getTuchID(LEN, view, blocks);

                if(check ==0)
                {
                    from=touchID;
                    from=0;
                    check++;
                }
                else if(check == 1)
                {
                    to=touchID;

                    Log.d("SheshBesh", "Got to:" + to + " and from: " + from);
                    blocks=gameManager.doMove(blocks,from,to,1);
                    upDate(blocks,LEN);
                    check = 0;
                }
                Toast.makeText(MainActivity.this, "You clicked the Linear Layout " + touchID +" ", Toast.LENGTH_SHORT).show();
            }
            else {
                check=0;
                from=0;
                to=3;
            }




        }

    }

    int[] testArr = new int[] {11,10,9,8,7,6,5,4,3,2,1,0,12,13,14,15,16,17,18,19,20,21,22,23};
    private int[] idArr = new int[24];
        public void createBlockStone(Block[] blocks) {
        //
        //
        for (int i = 0; i < this.LEN; i++) {
            LinearLayout correctLayout = findLayout(i);
            blocks[i] = new Block(getBaseContext(), Math.abs(stoneArragnement[i]),
                    stoneArragnement[i] > 0 ? 'w' : 'b');

            implementlistener(blocks[i],i);
//            blocks[i].setId(testArr[i]);
//            blocks[i].setId(i);


            if (i > 11) {

                blocks[i].setGravity(Gravity.RIGHT);

                if (i == 12) {
                    doMargtopScreen(i);
                } else {
                    doMargBottomUsual(i);
                }

            } else {
//                blocks[i].setId(11 - i - 1);
                doMargBottomUsual(i);
                if (i == 0) {
                    doMargtopScreen(i);
                }

            }

            if (i == 5 || i == 17) {
              doMargtopMiddleScreen(i);
            }


//            correctLayout.addView(blocks[i]);
//            if (i == 11)
//                reverse11(blocks);


        }

        addViewEnd(blocks,LEN);
        setOrderID(blocks, LEN);
        reverse11(blocks);
    }

    public static int  getTuchID(int len,View view,Block [] arr){
        for(Block b: arr) {
            Log.d("Shesh",  "Block View ID:" +  b.getId());
        }

        for (int t=0;t<len;t++){

             if(arr[t].getId() == view.getId()){
                 Log.d("Shesh", "Found block on ID: " + view.getId() + " i == " + t);
                 return view.getId();
             }
        }
        Log.d("Shesh", "Didn't find block!");
        return 0;

    }




    public void implementlistener(LinearLayout linearLayout,int i){
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Show a toast message.
//            }
//        });
        linearLayout.setOnClickListener(this);
    }



    private void doMargBottomUsual(int i) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 25);
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
        lp.setMargins(0, 0, 0, 150);
        blocks[i].setLayoutParams(lp);

    }


    private LinearLayout findLayout(int row) {
        return row < 12 ? verticalLayout1 : verticalLayout2;
    }

    private int dpToPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getApplicationContext().getResources().getDisplayMetrics());
    }

    private void pirntBlockId(Block []arr,int len){
        Log.d("Shesh","check: i== arr[i].getId()");
        for(int i=0;i<len;i++){

            if(i== arr[i].getId()){

                Log.d("Shesh", " ture   id:" + arr[i].getId() +"   i: "+i+" ");
                Log.d("Shesh", " num: " + arr[i].get_stoneNum() +" color:" +arr[i].get_color()+" ");
            }
            else
                Log.d("Shesh", " false  id:" + arr[i].getId() +"   i: "+i+" ");
        }

    }



    private void setOrderID(Block []arr,int len){

        for(int i=0;i<len;i++){
            if(i <= 11) {
                blocks[i].setId(11 - i);
                Log.d("SchoolTests", "NEW ID: "+ (11 - i) + " INDEX: " + i);
            }
            else
            {
                blocks[i].setId(i);
                Log.d("SchoolTests", "NEW ID: "+ (i) + " INDEX: " + i);
            }
        }

    }

    private void addViewEnd(Block[] arr ,int len){

        for(int i=0;i<len;i++){
            LinearLayout correctLayout = findLayout(i);
            correctLayout.addView(blocks[i]);

        }
    }

    private void reverse11(Block[] blockArr) {
        Block temp;

        for (int i = 0; i < 6; i++) {
            temp = blockArr[i];
            blockArr[i] = blockArr[11 - i];
            blockArr[11 - i] = temp;
        }

    }

    private  void  upDate(Block [] arr,int len){
        for(int i=0;i<len;i++){

            arr[i].upDate();
        }
    }

    @Override
    public int[] getScreenMesurment() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return new int[] {height, width};
    }

    @Override
    public int calclPresentOfScreen(int[] mesurment, int[] present) {
        return 0;
    }

    @Override
    public int[] calcMarginValues() {
        return new int[0];
    }


}