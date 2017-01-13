package kz.askar.datasorage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    DBHelper dbh;
    SQLiteDatabase db;
    EditText nameET;
    EditText phoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbh = new DBHelper(this);
        db = dbh.getWritableDatabase();

        nameET = (EditText)findViewById(R.id.name);
        phoneET = (EditText)findViewById(R.id.phone);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbh.close();
    }

    public void save(View v){
        ContentValues cv = new ContentValues();
        cv.put("name", nameET.getText().toString());
        cv.put("phone", phoneET.getText().toString());

        db.insert("contacts", null, cv);
        finish();
    }
}
