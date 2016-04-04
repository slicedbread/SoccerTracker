package com.bread.ian.soccertracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


public class GameRecord {
    private Date date;
    private ArrayList<GameEvent> eventList;
    public static ArrayList<GameRecord> recordList;
    public static SharedPreferences sharedpreferences;
    public static final String mPrefs = "MyPrefs";


    public GameRecord(Date d){
        date = d;
        eventList = new ArrayList<>();


    }

    public ArrayList<GameEvent> getList(){
        return eventList;
    }

    public Date getDate(){
        return date;
    }

    public void addGameEvent(int t, int x, int y){
        GameEvent g = new GameEvent(t,x,y);
        eventList.add(g);
    }

    @Override
    public String toString() {
        return "Game on " + date.toString();
    }

    public static ArrayList<GameRecord> getListFromPrefs(){
        String JSONString = sharedpreferences.getString(mPrefs, null);
        Type type = new TypeToken<ArrayList<GameRecord>>(){}.getType();
        recordList = new Gson().fromJson(JSONString, type);
        if (recordList == null)
            return new ArrayList<>();
        return recordList;
    }

    public void saveRecord(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        ArrayList<GameRecord> l = getListFromPrefs();
        l.add(this);
        String JSONString = new Gson().toJson(l);
        editor.putString(mPrefs, JSONString);
        editor.commit();
    }

    public static void onAppStart(){
        Context applicationContext = MainActivity.getContextOfApplication();
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        recordList = new ArrayList<>();
    }

}


