package ru.srba.testtouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    TextView tv;
    float d1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        tv = new TextView(this);
        setContentView(tv);
        tv.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        tv.setText(x + " : " + y);
        float d;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyLogs", "DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyLogs", "UP");
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 2) {
                    d = (float) Math.sqrt((Math.pow((event.getX(1) - event.getX(0)), 2)) + (Math.pow((event.getY(1) - event.getY(0)), 2)));
                    if (d > 0) {
                        tv.setTextSize(d/50);
                        Log.d("MyLogs", "Distance: " + (event.getX(1) - event.getX(0)) + " : " + (event.getY(1) - event.getY(0)) +" --------- "+ d);
                    }
                }
                break;
        }
        return true;
    }
}
