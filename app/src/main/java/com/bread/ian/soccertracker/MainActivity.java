package com.bread.ian.soccertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String mPrefs = "MyPrefs";
    public final static String SER_KEY = "com.bread.ian.soccertracker.ser";
    SharedPreferences sharedpreferences;
    public static Context contextOfApplication;


    private ArrayList<GameRecord> recordList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);

        contextOfApplication = getApplicationContext();

        GameRecord.onAppStart();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        recordList = GameRecord.getListFromPrefs();

        final GameRecord[] g = recordList.toArray(new GameRecord[recordList.size()]);
        String[] gname = new String[g.length];
        Date[] gdate = new Date[g.length];

        for (int i=0; i<g.length; i++) {
            gname[i] = g[i].getName();
            gdate[i] = g[i].getDate();
        }


        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<GameRecord> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, g) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(g[position].getName());
                text2.setText(g[position].getDate().toString());
                return view;
            }
        };
//        ArrayAdapter<Date> dateAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_2, android.R.id.text2, gdate);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item value
                GameRecord itemValue = (GameRecord) listView.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), EmailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(MainActivity.SER_KEY, itemValue);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }


}
