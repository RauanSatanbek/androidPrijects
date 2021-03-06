package ru.srba.volleytest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start extends AppCompatActivity implements View.OnClickListener{
    EditText et_name;
    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        et_name = (EditText)findViewById(R.id.et_name);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", et_name.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
        Log.d("MyLogs", "click");
    }
}
