package ru.srba.task6;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StorageActivity extends AppCompatActivity {

    EditText nameET;
    TextView nameTV;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        nameET = (EditText)findViewById(R.id.nameET);
        nameTV = (TextView)findViewById(R.id.nameTV);

        sp = getSharedPreferences("sdu_week7", MODE_PRIVATE);
        String nameFromSP = sp.getString("name", "");
        nameTV.setText(nameFromSP);
    }

    public void onEnter(View v){
        String nameFromSP = sp.getString("name", "");
        String name = nameET.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", nameFromSP+","+name);
        editor.commit();
    }


}

