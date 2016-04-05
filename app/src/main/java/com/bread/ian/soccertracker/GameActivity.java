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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.soccer_field);

    }

    public ArrayList<GameRecord> getListFromPrefs(){
        String JSONString = sharedpreferences.getString(mPrefs, null);
        Type type = new TypeToken<ArrayList<GameRecord>>(){}.getType();
        recordList = new Gson().fromJson(JSONString, type);
        if (recordList == null)
            return new ArrayList<>();
        return recordList;
    }

    public void addRecordToList(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        ArrayList<GameRecord> l = getListFromPrefs();
        g = field.returnRecord();
        l.add(g);
        String JSONString = new Gson().toJson(l);
        editor.putString(mPrefs, JSONString);
        editor.commit();
    }

}
