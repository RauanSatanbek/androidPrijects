package ru.srba.test_intent;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    Button btn_send;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_send = (Button)findViewById(R.id.send);
        btn_send.setOnClickListener(this);
        tv = (TextView)findViewById(R.id.input_name);
    }

    @Override
    public void onClick(View v) {
        String name = tv.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
