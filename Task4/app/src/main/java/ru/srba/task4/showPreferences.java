package ru.srba.task4;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class showPreferences extends AppCompatActivity {
    TextView tv_onCreate, tv_onStart, tv_onResume, tv_onRestart, tv_onPause, tv_onStop, tv_onDestroy;
    SharedPreferences sPref;

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    static String date;
    public final String Text_onCreate = "onCreate2", Text_onRestart = "onRestart2", Text_onStart = "onStart2",Text_onResume = "onResume2"
            , Text_onPause = "onPause2", Text_onStop = "onStop2",Text_onDestroy = "onDestroy2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_preferences);
        tv_onCreate = (TextView)findViewById(R.id.onCreate);
        tv_onStart = (TextView)findViewById(R.id.onStart);
        tv_onResume = (TextView)findViewById(R.id.onResume);
        tv_onRestart = (TextView)findViewById(R.id.onRestart);
        tv_onPause = (TextView)findViewById(R.id.onPause);
        tv_onStop = (TextView)findViewById(R.id.onStop);
        tv_onDestroy = (TextView)findViewById(R.id.onDestroy);


        sPref = getSharedPreferences("myPref", MODE_PRIVATE);
        saveText(Text_onCreate, getDate());
    }

    @Override
    protected void onStart() {
        super.onStart();
        saveText(Text_onStart, getDate());
    }
    @Override
    protected void onResume() {
        super.onResume();
        date = sdf.format(new Date(System.currentTimeMillis()));
        saveText(Text_onResume, getDate());


        sPref = getSharedPreferences("myPref", MODE_PRIVATE);
        tv_onCreate.setText("onCreate1: " + sPref.getString("onCreate1", "") + " - " + "onCreate2: " + sPref.getString("onCreate2", "") );
        tv_onStart.setText("onStart1: " + sPref.getString("onStart1", "") + " - " + "onStart2: " + sPref.getString("onStart2", ""));
        tv_onResume.setText("onResume1: " + sPref.getString("onResume1", "") + " - " + "onResume2: " + sPref.getString("onResume2", ""));
        tv_onRestart.setText("onRestart1: " + sPref.getString("onRestart1", "") + " - " + "onRestart2: " + sPref.getString("onRestart2", ""));
        tv_onPause.setText("onStart1: " + sPref.getString("onStart1", "") + " - " + "onStart2: " + sPref.getString("onStart2", ""));
        tv_onStop.setText("onResume1: " + sPref.getString("onResume1", "") + " - " + "onResume2: " + sPref.getString("onResume2", ""));
        tv_onDestroy.setText("onDestroy1: " + sPref.getString("onDestroy1", "") + " - " + "onDestroy2: " + sPref.getString("onDestroy2", ""));
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        saveText(Text_onRestart, getDate());
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveText(Text_onPause, getDate());
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveText(Text_onStop, getDate());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText(Text_onDestroy, getDate());
//        SharedPreferences.Editor edit = sPref.edit();
//        edit.remove(Text_onCreate);
//        edit.commit();
    }
    public void saveText(String key, String value){
        SharedPreferences.Editor edit = sPref.edit();
        edit.putString(key, value);
        edit.commit();
    }
    public  String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        return date;
    }
}
