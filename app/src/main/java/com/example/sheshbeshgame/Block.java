package com.example.sheshbeshgame;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

    public class Block extends LinearLayout {

        int  _stoneNum;
        Character _color;
        private Context context;

        public Block(Context context, int _stoneNum, Character _color) {
            super(context);
            this._stoneNum = _stoneNum;
            this._color = _color;
            this.context=context;
            this.setOrientation(LinearLayout.HORIZONTAL);

            upDate();

        }





        public int get_stoneNum() {
            return _stoneNum;
        }

        public void set_stoneNum(int _stoneNum) {
            this._stoneNum = _stoneNum;
        }

        public Character get_color() {
            return _color;
        }

        public void set_color(Character _color) {
            this._color = _color;
        }


        public void upDate (){
            this.removeAllViews();

            if(this._stoneNum==0){
                ImageView iv= new ImageView(this.getContext());
                iv.setImageResource(R.drawable.p2);
                iv.setVisibility(INVISIBLE);
                this.addView(iv);
            }


            for(int i=0;i<this._stoneNum;i++){
                ImageView iv= new ImageView(this.getContext());
                if(this._color=='b')
                {
                    iv.setImageResource(R.drawable.p1);
                    //    iv.setVisibility(VISIBLE);
                   // iv.setMaxWidth(10);
                   // iv.setMaxHeight(10);


                   this.addView(iv);
                    System.out.println(i + ": " + iv.getId());

                }
                else
                {
                    iv.setImageResource(R.drawable.p2);

                    this.addView(iv);
                }

            }
        }
}
