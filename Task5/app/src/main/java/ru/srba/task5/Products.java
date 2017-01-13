package ru.srba.task5;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Products extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    DBHelper dbHelper;
    Button addNewProducts;
    Intent intent;
    TextView textView;
    int categoryId;
    HashMap<String, Integer> productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);
        addNewProducts = (Button)findViewById(R.id.addNewProducts);
        addNewProducts.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        textView = (TextView)findViewById(R.id.textView) ;
        intent = getIntent();
        textView.setText("" + intent.getStringExtra("categoryName"));
        categoryId = intent.getIntExtra("categoryId", 0);
        refresh();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, addToDataBase.class);
        intent.putExtra("addNew", 2);
        intent.putExtra("categoryId", categoryId);
        startActivityForResult(intent, 1);
    }
    public void refresh(){
        productName = new HashMap<>();
        ArrayList<String> m = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "category_id = " + categoryId;
        Cursor cursor = db.query("Products", null, selection, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                productName.put(name, id);
                m.add(name);
            }while(cursor.moveToNext());
            cursor.close();
        }
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, m));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) refresh();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1, "Delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int productId = productName.get(listView.getItemAtPosition(info.position));
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("Products", "_id = " + productId, null);
        db.close();
        refresh();
        return super.onContextItemSelected(item);
    }
}
