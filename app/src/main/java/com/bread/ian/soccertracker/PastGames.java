package com.bread.ian.soccertracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class PastGames extends Activity {

    public static final String mPrefs = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    ArrayList<GameRecord> recordList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_games);
        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);
        recordList = getListFromPrefs();

        GameRecord[] g = recordList.toArray(new GameRecord[recordList.size()]);
        String[] dates = new String[g.length];
        for(int i = 0; i<g.length; i++)
        {
            dates[i] = g[i].getDate().toString();
        }

        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, dates);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_past_games, menu);
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
        String JSONString = sharedpreferences.getString(mPrefs, null);
        Type type = new TypeToken<ArrayList<GameRecord>>(){}.getType();
        recordList = new Gson().fromJson(JSONString, type);
        if (recordList == null)
            return new ArrayList<>();
        return recordList;
    }
}
