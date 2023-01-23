package com.example.sheshbeshgame;

public interface BoardDesign {

   int []  getScreenMesurment ();
   // מחזיר מערך עם רכי אורח ורוחב של מסך נוכחי

   int calclPresentOfScreen(int [] mesurment, int [] present);
    // מחזיר ערכי אורך רוחב  של אובייקט

    int [] calcMarginValues();
    // מחזיר ערכי ה מרגין  של למעלה למטה ימין שמאל

}
