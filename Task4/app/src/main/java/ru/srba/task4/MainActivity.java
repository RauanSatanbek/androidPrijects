package ru.srba.task4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    static Button go;
    static TextView text;
    static SharedPreferences sPref;
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    static String date;
    public final String Text_onCreate = "onCreate1", Text_onRestart = "onRestart1", Text_onStart = "onStart1",Text_onResume = "onResume1"
            , Text_onPause = "onPause1", Text_onStop = "onStop1",Text_onDestroy = "onDestroy1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = (Button)findViewById(R.id.goToActivity2);
        go.setOnClickListener(this);
        sPref = getSharedPreferences("myPref", MODE_PRIVATE);

        text = (TextView)findViewById(R.id.text);
        String str = sPref.getString(Text_onCreate, "");
        if(str.equals("")) text.setText("HELLO!!!");
        else text.setText("");
//        setText(text);

        saveText(Text_onCreate, getDate());
    }
    public void setText(TextView text){
        String str = sPref.getString(Text_onCreate, "");
        if(str.equals("")) text.setText("HELLO!!!");
        else text.setText("");
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
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        setText(text);
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
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.goToActivity2:
                Intent intent = new Intent(this, showPreferences.class);
                startActivity(intent);
                break;
        }
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