package ru.srba.test_db;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_add, btn_read;
    TextView tv_name;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_name = (TextView)findViewById(R.id.tv_name);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_add.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }
    @Override
    public void onClick(View v) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.btn_add:
                contentValues.put(DBHelper.NAME, tv_name.getText().toString());
                database.insert(DBHelper.TABLE_NAME, null, contentValues);
                database.close();
                break;
            case R.id.btn_read:
                Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    int NAME = cursor.getColumnIndex(DBHelper.NAME);
                    do{
                        Log.d("myLogs", cursor.getString(NAME));
                    }while (cursor.moveToNext());
                }else Log.d("myLogs", "0 rows");
        }
    }
}
