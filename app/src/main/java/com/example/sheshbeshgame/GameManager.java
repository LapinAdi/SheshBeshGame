package com.example.sheshbeshgame;

import android.content.Context;
import android.widget.LinearLayout;

public class GameManager {

        boolean isGameGoing;
        boolean turn ; // white- true / black - false
        int pointsW;
        int pointsB;

        public GameManager() {
                this.isGameGoing = true;
                this.turn = true;
                this.pointsW = 0;
                this.pointsB = 0;
        }

        public boolean isGameGoing() {
                return isGameGoing;
        }

        public void setGameGoing(boolean gameGoing) {
                isGameGoing = gameGoing;
        }

        public boolean getTurn() {
                return turn;
        }

        public void setTurn(boolean turn) {
                this.turn = turn;
        }

        public int getPointsW() {
                return pointsW;
        }

        public void setPointsW(int pointsW) {
                this.pointsW = pointsW;
        }

        public int getPointsB() {
                return pointsB;
        }

        public void setPointsB(int pointsB) {
                this.pointsB = pointsB;
        }


        public Block[] doMove(Block [] arr ,int from,int to,int steps){

                arr[from].set_stoneNum(arr[from].get_stoneNum()-steps);
                arr[to].set_stoneNum(arr[to].get_stoneNum()+steps);

                return arr;
        }


}