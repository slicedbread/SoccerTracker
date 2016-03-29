package com.bread.ian.soccertracker;

/**
 * Created by ian on 3/29/16.
 */
public class GameEvent {
    // #1 misses
    // #2 goals
    // #3 given foul
    // #4 relieved foul
    int type;
    int xLoc;
    int yLoc;

    public GameEvent(int t, int x, int y){
        type = t;
        xLoc = x;
        yLoc = y;
    }

}

