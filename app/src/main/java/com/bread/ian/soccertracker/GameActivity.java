package com.bread.ian.soccertracker;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

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
