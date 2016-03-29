package com.bread.ian.soccertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String mPrefs = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    ArrayList<GameRecord> recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addRecordToList(new GameRecord(new Date()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRecordToList(new GameRecord(new Date()));
            }
        });


    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void viewOldGames(View view){
        Intent intent = new Intent(this, PastGames.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<GameRecord> getListFromPrefs(){
        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);
        String JSONString = getPreferences(MODE_PRIVATE).getString(mPrefs, null);
        Type type = new TypeToken<ArrayList<GameRecord>>(){}.getType();
        recordList = new Gson().fromJson(JSONString, type);
        if (recordList == null)
            return new ArrayList<GameRecord>();
        return recordList;
    }

    public void addRecordToList(GameRecord g){
        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        ArrayList<GameRecord> l = getListFromPrefs();
        l.add(g);
        String JSONString = new Gson().toJson(l);
        editor.putString(mPrefs, JSONString);
        editor.commit();
    }
}
