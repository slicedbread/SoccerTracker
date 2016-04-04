package com.bread.ian.soccertracker;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    public void toEmailActivity(View view) {
        Intent intent = new Intent(this, EmailActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //below only work for extends Activity and old API
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //below only work for no hiding setting in XML
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
        
        setContentView(R.layout.activity_game);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        GameEventMenu gMenu = new GameEventMenu(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.soccer_field);

    }

}
