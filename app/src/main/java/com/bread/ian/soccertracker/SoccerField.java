package com.bread.ian.soccertracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Color;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Alex on 3/31/2016.
 */
public class SoccerField extends View implements View.OnTouchListener{

    private Paint  mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private GameRecord g;
    private int current_event = 1;


    private Bitmap eventType;

    //private HashMap pointerMap;

    public SoccerField(Context context) {
        super(context);



        
        initDotsView();
    }

    public SoccerField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDotsView();
    }

    public SoccerField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDotsView();
    }

    public SoccerField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDotsView();
    }

    public void setEventType(String s) {
        if (s.equals("GOAL")) {
            eventType = BitmapFactory.decodeResource(getResources(), R.drawable.soccerball);
            current_event = 1;
        } else if (s.equals("MISS")) {
            eventType = BitmapFactory.decodeResource(getResources(), R.drawable.missball);
            current_event = 2;
        } else if (s.equals("FOUL")) {
            eventType = BitmapFactory.decodeResource(getResources(), R.drawable.foulball);
            current_event = 3;
        }
    }

    public GameRecord returnRecord() {
        return g;
    }

    private void initDotsView() {
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
      //  pointerMap = new HashMap();
        setOnTouchListener(this);

        // create new Game Record
        g = new GameRecord(new Date());

        eventType = BitmapFactory.decodeResource(getResources(), R.drawable.soccerball);
        //setDotRadius(SMALL_RADIUS);
        //setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint.setStrokeWidth((float) 20);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    public boolean onTouch(View v, MotionEvent event) {
        // Removed support for multitouch.
            int action = event.getActionMasked();
            int index = event.getActionIndex();
            //int id = event.getPointerId(index);
            float x = event.getX(index);
            float y = event.getY(index);
            Point p;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
               // case MotionEvent.ACTION_POINTER_DOWN:
                    p = new Point((int)x, (int)y);
                    //pointerMap.put(id, p);
                    mCanvas.drawBitmap(eventType,x,y,new Paint());
                    g.addGameEvent(current_event,(int)x,(int)y);
                    invalidate();
                    break;
                default:
                    break;
            }
            return true;
        }
    }
