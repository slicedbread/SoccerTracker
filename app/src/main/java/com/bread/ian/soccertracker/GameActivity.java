package com.bread.ian.soccertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class GameActivity extends AppCompatActivity {

    public static final String mPrefs = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    GameRecord g;
    SoccerField field;


    public void toEmailActivity(View view) {
        //field = (SoccerField) findViewById(R.id.soccerfield);
        //field.saveImage();
        takeScreenshot();
        // stores GameRecord
        addRecordToList();
        Intent intent = new Intent(this, EmailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.SER_KEY,g);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedpreferences = getSharedPreferences(mPrefs, Context.MODE_PRIVATE);
        GameEventMenu gMenu = new GameEventMenu(this);

        field = (SoccerField) findViewById(R.id.soccerfield);


    }
    public void addRecordToList(){
        g = field.returnRecord();
        g.saveRecord();
    }

    private void takeScreenshot() {
        Date now = field.returnRecord().getDate();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            String folder_main = "soccer_tracker";

            File f = new File(Environment.getExternalStorageDirectory(), folder_main);
            if (!f.exists()) {
                f.mkdirs();
            }
            String mPath = Environment.getExternalStorageDirectory().toString() + "/soccer_tracker/" + now + ".jpg";
            //Log.d("Field"," "+ mPath);
            // create bitmap screen capture only for the soccerfield
            View v1 = getWindow().getDecorView().getRootView().findViewById(R.id.soccerfield);
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();


        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

}
