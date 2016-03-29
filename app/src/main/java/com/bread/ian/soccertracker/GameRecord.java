package com.bread.ian.soccertracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ian on 3/29/16.
 */
public class GameRecord {
    private Date date;
    private ArrayList<GameEvent> eventList;

    public GameRecord(Date d){
        date = d;
        eventList = new ArrayList<GameEvent>();
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

