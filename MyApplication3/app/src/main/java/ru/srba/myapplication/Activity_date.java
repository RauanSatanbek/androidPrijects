package ru.srba.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_date extends AppCompatActivity implements View.OnClickListener{
    Button btn_left, btn_right, btn_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_date);
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_right = (Button)findViewById(R.id.btn_right);
        btn_center = (Button)findViewById(R.id.btn_center);

        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_center.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_left:
                intent.putExtra("align", Gravity.LEFT);
                break;
            case R.id.btn_right:
                intent.putExtra("align", Gravity.RIGHT);
                break;
            case R.id.btn_center:
                intent.putExtra("align", Gravity.CENTER);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
