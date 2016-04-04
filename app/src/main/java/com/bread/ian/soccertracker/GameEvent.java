package com.bread.ian.soccertracker;

/**
 * Created by ian on 3/29/16.
 */
public class GameEvent {
    // #1 goals
    // #2 misses
    // #3 given foul
    // #4 relieved foul - What does this mean?
    int type;
    int xLoc;
    int yLoc;

    public GameEvent(int t, int x, int y){
        type = t;
        xLoc = x;
        yLoc = y;
    }

}

