package com.example.sheshbeshgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class AddMyPlayer extends AppCompatActivity implements View.OnClickListener {

    // שלב א - הגדרת הרכיבים שנרצה לתכנת

    private Button btnPlay,      //כפתור
            btnAddBack;          //

    private EditText etName1,  //
            etName2;            //

    // Content Provider  - Contact List
    private Button btnContact1,
            btnContact2;
    private int playerNum;
    // for Intent
    private static final int RESULTPICK=1;


    private TextToSpeech textToSpeech; //כשעוברים למסך הבא  מופעל רמקול שאומר את השמות

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_player);

        // שלב ב - קישור לרכיבים במסך

        btnPlay=(Button) findViewById(R.id.btnPlay);
        btnAddBack=(Button) findViewById(R.id.btnPlayerBack);

        etName1=(EditText) findViewById(R.id.etName1);
        etName2=( EditText) findViewById(R.id.etName2);

        btnPlay.setOnClickListener(this);

        //מאתחל  את הטקסט טו ספיצ
        textToSpeech = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i){
                if(i== TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        //   connect Layout to Java
        btnContact1 = (Button) findViewById(R.id.btnContact1);
        btnContact1.setOnClickListener(this);

        btnContact2 = (Button) findViewById(R.id.btnContact2);
        btnContact2.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.btnContact1:
                this.playerNum=1;
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent,RESULTPICK);
                break;
            case R.id.btnContact2:
                this.playerNum=2;
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent,RESULTPICK);
                break;
            default:
                intent=new Intent(this, MainActivity.class);



                String name1 = etName1.getText().toString();
                String name2 =etName2.getText().toString();



                if (name1.length() ==0 || name2.length()==0|| name1.equals(name2)){
                    new AlertDialog.Builder(this)
                            .setTitle("ERORE")
                            .setMessage("not a good name")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setIcon(R.drawable.error)
                            .show();
                }
                else{
                    //משמיע טקסט

                    textToSpeech.speak("Enjoy and Good Luck! " + name1 + " and " + name2, TextToSpeech.QUEUE_FLUSH, null);

                    intent.putExtra("DATA1",name1);
                    intent.putExtra("DATA2", name2);

                    startActivity(intent);

                }


        }
        if (v.getId()==btnAddBack.getId()){
            finish();
        }






}


    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK && requestCode ==1)
        {
            Uri uri = data.getData();

            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();

            // int phoneIndexNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int phoneIndexName = cursor.getColumnIndex
                    (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            // String phoneNumber = cursor.getString(phoneIndexNumber);
            String phoneName = cursor.getString(phoneIndexName);

            if (this.playerNum ==1)
                etName1.setText(phoneName);
            else
                etName2.setText(phoneName);

        }
    }


}