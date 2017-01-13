package ru.srba.task5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addToDataBase extends AppCompatActivity  implements View.OnClickListener{
    DBHelper dbHelper;
    EditText editText;
    Button addNew;
    Intent intent;
    int addNewIntent; /*Переменная из Intent 1 || 2*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_data_base);
        intent = getIntent();
        dbHelper = new DBHelper(this);
        editText = (EditText)findViewById(R.id.category);
        addNew = (Button)findViewById(R.id.addNew);
        addNew.setOnClickListener(this);
        addNewIntent = intent.getIntExtra("addNew", 0);
        switch (addNewIntent){
            case 1:
                editText.setHint("Categories");
                break;
            case 2:
                editText.setHint("Products");
                break;
        }
    }
    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        String tableName = "";
        switch (addNewIntent){
            case 1:
                tableName = "Categories";
                contentValues.put("name", editText.getText().toString());
                break;
            case 2:
                tableName = "Products";
                contentValues.put("name", editText.getText().toString());
                contentValues.put("category_id", intent.getIntExtra("categoryId", 0));
                break;

        }
        db.insert(tableName, null, contentValues);
        db.close();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
