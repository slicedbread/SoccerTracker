package com.bread.ian.soccertracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmailActivity extends Activity {

    private ArrayList<GameEvent> eventList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        // Load Data from shared Preferences

        // Format Shared Data

        GameRecord game = (GameRecord)getIntent().getSerializableExtra(MainActivity.SER_KEY);

        eventList = game.getList();

        GameEvent[] g = eventList.toArray(new GameEvent[eventList.size()]);


        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<GameEvent> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, g);
        listView.setAdapter(adapter);


    }


    // Send Email Activity
    public void sendEmail(View view) {

        //TODO: Replace static data with real data
        String[] emails = {""};
        String subject = "Game identifier";
        String message = "X: 20.3 Y: 30.2 Event: Goal";

        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","alextwotu@gmail.com", null));
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        //email.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(email, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
