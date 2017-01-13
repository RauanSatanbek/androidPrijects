package ru.srba.test_intent;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_add, btn_read, btn_clear;
    TextView tv_name, tv_email;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        tv_name = (TextView)findViewById(R.id.input_name);
        tv_email = (TextView)findViewById(R.id.input_mail);

        btn_add.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        String name = tv_name.getText().toString();
        String email = tv_email.getText().toString();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (v.getId()){
            case R.id.btn_add:
                contentValues.put(DBHelper.Key_name, name);
                contentValues.put(DBHelper.Key_mail, email);
                sqLiteDatabase.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
            case R.id.btn_read:
                Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_CONTACTS, null ,null ,null, null, null, null);
                if(cursor.moveToFirst()){
                        int idIndex = cursor.getColumnIndex(DBHelper.Key_id);
                        int nameIndex = cursor.getColumnIndex(DBHelper.Key_name);
                        int mailIndex = cursor.getColumnIndex(DBHelper.Key_mail);
                        do{
                            Log.d("MyLogs", cursor.toString());
                            Log.d("MyLogs", "ID - " + cursor.getInt(idIndex) +
                                    ", Name - " + cursor.getString(nameIndex) +
                                    ", Mail - " + cursor.getString(mailIndex));
                        }while(cursor.moveToNext());
                }else Log.d("MyLogs", "0 rows");
                cursor.close();
                break;
            case R.id.btn_clear:
                break;
        }
    }
}
