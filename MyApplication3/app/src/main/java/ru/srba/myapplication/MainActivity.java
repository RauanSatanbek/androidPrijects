package ru.srba.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button color, align;
    TextView name;
    final int TEXT_COLOR = 1, TEXT_ALIGN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView)findViewById(R.id.textView);
        color = (Button)findViewById(R.id.btn_color);
        align = (Button)findViewById(R.id.btn_align);
        color.setOnClickListener(this);
        align.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_color:
                intent = new Intent(this, Activity_time.class);
                startActivityForResult(intent, TEXT_COLOR);
                break;
            case R.id.btn_align:
                intent = new Intent(this, Activity_date.class);
                startActivityForResult(intent, TEXT_ALIGN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("myLogs", "requestCode: " + requestCode + ", " + "resultCode: " + resultCode);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case TEXT_COLOR:
                    int color = data.getIntExtra("color", Color.BLACK);
                    name.setTextColor(color);
                    break;
                case TEXT_ALIGN:
                    int align = data.getIntExtra("align", Gravity.LEFT);
                    name.setGravity(align);
                    break;
            }
        } else name.setTextColor(Color.GRAY);
    }
}
