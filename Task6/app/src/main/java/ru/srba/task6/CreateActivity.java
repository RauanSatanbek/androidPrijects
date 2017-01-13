package ru.srba.task6;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbh;
    SQLiteDatabase db;
    EditText nameET;
    EditText phoneET;
    Button btn_save;
    String action = "";
    long edit_id;
    Intent getIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        dbh = new DBHelper(this);
        db = dbh.getWritableDatabase();
        nameET = (EditText)findViewById(R.id.name);
        phoneET = (EditText)findViewById(R.id.phone);
        getIntent = getIntent();
        action = getIntent.getStringExtra("action");
        edit_id = getIntent.getLongExtra("id", 0);
        btn_save = (Button)findViewById(R.id.btn_save);
        if(action.equals("update")) {
            String text[] = getIntent.getStringExtra("text").split(":");
            nameET.setText(text[0]);
            phoneET.setText(text[1]);
            btn_save.setText("Update");
        }
        btn_save.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbh.close();
    }
    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        cv.put("name", nameET.getText().toString());
        cv.put("phone", phoneET.getText().toString());
        if(action.equals("update")){
            Log.d("myLogs", "id = " + edit_id);
            db.update("contacts", cv, "_id = " + edit_id ,null);
        } else {
            db.insert("contacts", null, cv);
        }
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}

