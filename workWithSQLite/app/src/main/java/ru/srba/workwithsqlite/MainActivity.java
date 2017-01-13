package ru.srba.workwithsqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name[] = {"Китай", "США", "Бразилия", "Россия", "Япония" , "Германия", "Египет", "Европа", "Америка"};
        int people [] = {1400, 311, 195, 142, 128, 82, 60, 66, 35};
        String region[] = {"Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа", "Америка"};
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        String c[] = {"name", "people"};
        Cursor cursor = db.query("Countries", c, "people > 100", null, null, null, "people");
        cursor.moveToFirst();
        do{
            String str = "";
            for(String cn : cursor.getColumnNames()){
                str = str.concat(cn + " - " + cursor.getString(cursor.getColumnIndex(cn)) + ";");
            }
            Log.d("myLogs", str);
        }while (cursor.moveToNext());
        cursor.close();
        dbHelper.close();
    }
    @Override
    public void onClick(View v) {

    }
}
