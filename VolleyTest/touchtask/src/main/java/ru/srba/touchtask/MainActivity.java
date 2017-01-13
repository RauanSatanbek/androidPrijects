package ru.srba.touchtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    TextView textView;
    AbsoluteLayout al;
    Button basket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        basket = (Button) findViewById(R.id.basket);
        al = (AbsoluteLayout) findViewById(R.id.al);
        al.setOnTouchListener(this);
        basket.setX(300);
        basket.setY(900);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.d("MyLogs", x + " : " + y);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyLogs", "DOWN");
                textView.setX(x);
                textView.setY(y);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyLogs", "UP");
                break;
            case MotionEvent.ACTION_MOVE:
                textView.setX(x);
                textView.setY(y);
                if((x >= 300 && x <= 450) && (y >= 900 && y <= 1000)) {
                    Log.d("MyLogs", "Yes");
                    al.removeView(textView);
                } else {
                    Log.d("MyLogs", "No");
                }
                break;
        }
        return true;
    }
}
