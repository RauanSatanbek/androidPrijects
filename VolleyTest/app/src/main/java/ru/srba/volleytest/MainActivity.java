package ru.srba.volleytest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    TextView tv;
    ListView listView;
    float x, y;
    String up, down, move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);

        Intent intent = new Intent(this, Start.class);
        startActivityForResult(intent, 1);
        tv = (TextView) findViewById(R.id.tv);

        listView = (ListView)findViewById(R.id.listView);

        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        HashMap<String, String> map;
        for(int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put("name", "Room " + (i + 1));
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_1,
            new String[]{"name"},
            new int[]{android.R.id.text1}
        );
        listView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MyLogs", "onActivityResult");
        if(resultCode == RESULT_OK) {
            tv.setText(data.getStringExtra("name"));
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down = "Down: x - " + x + ": y - " + y;
                Log.d("MyLogs", down);
                up = "";
                move = "";
                break;
            case MotionEvent.ACTION_MOVE:
                move = "Move: x - " + x + ": y - " + y;
                Log.d("MyLogs", move);
                break;
            case MotionEvent.ACTION_UP:
                move = "";
                up = "Up: x - " + x + ": y - " + y;
                Log.d("MyLogs", up);
                break;
        }
        tv.setText(down + "\n" + move + "\n" + up);
        return true;
    }
}
