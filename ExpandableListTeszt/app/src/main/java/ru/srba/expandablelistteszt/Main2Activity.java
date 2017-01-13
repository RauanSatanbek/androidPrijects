package ru.srba.expandablelistteszt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView textView = (TextView)findViewById(R.id.tvName);
        textView.setText(name);
    }

    public void onBackPressed(){
        Toast.makeText(this, "dsad", Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
