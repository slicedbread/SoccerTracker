package com.bread.ian.soccertracker;

import java.util.ArrayList;
import java.util.Date;


public class GameRecord {
    private Date date;
    private ArrayList<GameEvent> eventList;
    private static int currID = 0;
    private int id;


    public GameRecord(Date d){
        date = d;
        eventList = new ArrayList<>();
        id = ++currID;

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
}


