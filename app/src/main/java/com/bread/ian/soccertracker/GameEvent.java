package com.bread.ian.soccertracker;


public class GameEvent {
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

}

