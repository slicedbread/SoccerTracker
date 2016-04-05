package com.bread.ian.soccertracker;


import java.io.Serializable;

public class GameEvent implements Serializable {
    // #1 goals
    // #2 misses
    // #3 given foul
    int type;
    int xLoc;
    int yLoc;

    public GameEvent(int t, int x, int y){
        type = t;
        xLoc = x;
        yLoc = y;
    }

    @Override
    public String toString(){
        String typeStr = "err";
        if(type == 1){
            typeStr = "goal";
        }
        else if(type == 2){
            typeStr = "missed shot";
        }
        else if(type == 3){
            typeStr = "Foul";
        }
        return typeStr + " at X,Y (" + xLoc + "," + yLoc + ")";
    }

}

