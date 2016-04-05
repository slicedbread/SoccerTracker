package com.bread.ian.soccertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public static final String mPrefs = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    ArrayList<GameRecord> recordList;
    GameRecord g;
    SoccerField field;


    public void toEmailActivity(View view) {
        // stores GameRecord
        addRecordToList();
        Intent intent = new Intent(this, EmailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.SER_KEY,g);
        intent.putExtras(bundle);

        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);
        GameEventMenu gMenu = new GameEventMenu(this);

        field = (SoccerField) findViewById(R.id.soccerfield);


    }


    public void addRecordToList(){
        g = field.returnRecord();
        g.saveRecord();
    }

}
