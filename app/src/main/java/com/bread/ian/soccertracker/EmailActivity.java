package com.bread.ian.soccertracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

public class EmailActivity extends Activity {

    private ArrayList<GameEvent> eventList;
    private ListView listView;
    GameEvent[] g;
    String totals;
    String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        totals = "";
        results = "";

        GameRecord game = (GameRecord)getIntent().getSerializableExtra(MainActivity.SER_KEY);

        eventList = game.getList();

        g = eventList.toArray(new GameEvent[eventList.size()]);


        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<GameEvent> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, g);
        listView.setAdapter(adapter);

        int numOfGoals = 0;
        int numOfMisses = 0;
        int numOfFouls = 0;

        for(int i = 0; i < g.length; i++) {
            results += (g[i].toString() + '\n');
            if(g[i].type == 1) {
                numOfGoals++;
            }
            else if(g[i].type == 2) {
                numOfMisses++;
            }
            else {
                numOfFouls++;
            }
        }

        totals += "number of goals scored: " + numOfGoals + '\n';
        totals += "number of misses scored: " + numOfMisses + '\n';
        totals += "number of fouls scored: " + numOfFouls + '\n';
        results += totals;

        TextView t = (TextView) findViewById(R.id.resultTextView);
        t.setText(totals);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.emailfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] emails = {""};
                String subject = "Game Record";
                String message = results;

                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","", null));

                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);




                // need this to prompts email client only
               // email.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(email, "Send mail..."));
                    Log.i("Finished sending email.", "");
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(EmailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showImage(View view){
        GameRecord game = (GameRecord)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        Date now = game.getDate();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        String mPath = Environment.getExternalStorageDirectory().toString() + "/soccer_tracker/" + now + ".jpg";
        Intent image = new Intent();
    //    Log.d("Field", "H "+ Uri.parse("file:/" + mPath));
        image.setAction(Intent.ACTION_VIEW);
        Uri identifier = Uri.fromFile(new File(mPath));
        image.setDataAndType(identifier,"image/*");
        startActivity(image);

    }

}
