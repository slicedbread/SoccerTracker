package com.bread.ian.soccertracker;

import android.app.Activity;
import android.graphics.Color;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

/**
 * Created by Alex on 4/3/2016.
 */
public class GameEventMenu  implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    private Activity activity;
    private Button wSelectionButton;
    private PopupMenu wSelectionMenu;

    public GameEventMenu(Activity activity) {
        this.activity = activity;
        wSelectionButton = (Button) activity.findViewById(R.id.button);
        wSelectionButton.setOnClickListener(this);

        wSelectionMenu = new PopupMenu(activity, wSelectionButton);
        MenuInflater inflater = wSelectionMenu.getMenuInflater();
        inflater.inflate(R.menu.game_event_menu, wSelectionMenu.getMenu());
        wSelectionMenu.setOnMenuItemClickListener(this);


    }

    public boolean onMenuItemClick(MenuItem item) {
        item.setChecked(true);
        SoccerField field = (SoccerField) activity.findViewById(R.id.soccerfield);
        switch (item.getItemId()) {
            case R.id.goal:
                field.setEventType("GOAL");
                wSelectionButton.setText("GOAL");
                break;
            case R.id.miss:
                field.setEventType("MISS");
                wSelectionButton.setText("MISS");
                break;
            case R.id.foul:
                field.setEventType("FOUL");
                wSelectionButton.setText("FOUL");
                break;
            default:
                break;
        }
        return true;
    }
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button:
                wSelectionMenu.show();
                break;
        }
    }

}
