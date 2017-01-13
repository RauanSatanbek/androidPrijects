package ru.srba.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Activity_time extends AppCompatActivity implements View.OnClickListener{
    TextView tv_name;
    Button btn_red, btn_green, btn_blue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_time);
        btn_red = (Button)findViewById(R.id.btn_red);
        btn_green = (Button)findViewById(R.id.btn_green);
        btn_blue = (Button)findViewById(R.id.btn_blue);
        btn_red.setOnClickListener(this);
        btn_green.setOnClickListener(this);
        btn_blue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){
            case R.id.btn_red:
                intent.putExtra("color", Color.RED);
                break;
            case R.id.btn_green:
                intent.putExtra("color", Color.GREEN);
                break;
            case R.id.btn_blue:
                intent.putExtra("color", Color.BLUE);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
