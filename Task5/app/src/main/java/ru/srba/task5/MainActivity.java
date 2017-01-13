package ru.srba.task5;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    DBHelper dbHelper;
    Button addNewCategory;
    HashMap<String, Integer> ids;
    ListViewCompat selectedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        addNewCategory = (Button)findViewById(R.id.addNewCategory);
        addNewCategory.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listView);
        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int categoryId = 0 + ids.get(listView.getItemAtPosition(position));
                Intent intent = new Intent(MainActivity.this, Products.class);
                intent.putExtra("categoryId", categoryId);
                intent.putExtra("categoryName", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "Delete");
        super.onCreateContextMenu(menu, v, menuInfo);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        int categoryId = ids.get(listView.getItemAtPosition(info.position));
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("Categories", "_id = " + categoryId, null);
        db.delete("Products", "category_id = " + categoryId, null);
        db.close();
        refresh();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewCategory:
                Intent intent = new Intent(this, addToDataBase.class);
                intent.putExtra("addNew", 1);
                startActivityForResult(intent, 1);
                break;
        }
    }
    public void refresh(){
        ids = new HashMap<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("Categories", null, null, null, null, null, null);
        ArrayList<String> m = new ArrayList<>();
        if(cursor.moveToFirst()){
            int c = 0;
            do{
                c++;
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                ids.put(name, id);
                m.add(name);

            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, m);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) refresh();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
