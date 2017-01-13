package ru.srba.task6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh;
    SQLiteDatabase db;
    final int MENU_UPDATE = 1;
    final int MENU_DELETE = 2;
    String userInfo[];
    long changableId = 0;
    String textFromTv;
    HashMap<String, Integer> ids = new HashMap<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);
        dbh = new DBHelper(this);
        db = dbh.getWritableDatabase();

        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbh.close();
    }

    public void addContact(View v){
        Intent i = new Intent(this, CreateActivity.class);
        i.putExtra("action", "create");
        startActivityForResult(i, 2);
    }

    public void refresh(){
        LinearLayout.LayoutParams llp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        Cursor c = db.query("contacts", null, null, null, null, null, "name");
        int count = 0;
        ArrayList<HashMap<String, String>> m = new ArrayList<>();
        HashMap<String, String> map;
        if(c.moveToFirst()){
            do{
                map = new HashMap<>();
                count++;
                int id = c.getInt(c.getColumnIndex("_id"));
                String name = c.getString(c.getColumnIndex("name"));
                String phone = c.getString(c.getColumnIndex("phone"));
                Log.d("myLogs", name + ":" + phone );
                map.put("name", name);
                map.put("phone", phone);
                m.add(map);
                ids.put("" + count, id);
            }while(c.moveToNext());
            Log.d("myLogs", ids.toString());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, m);
            String from[] = {"name", "phone"};
            int to [] = {R.id.name, R.id.phone};
            SimpleAdapter adapter = new SimpleAdapter(this, m, R.layout.text,from, to);
            listView.setAdapter(adapter);


        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU_UPDATE, 0, "Update");
        menu.add(0, MENU_DELETE, 1, "Delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index = info.position + 1;
        LinearLayout ll = (LinearLayout)info.targetView;
        TextView tv_name = (TextView)ll.findViewById(R.id.name);
        TextView tv_phone = (TextView)ll.findViewById(R.id.phone);
        changableId = Long.parseLong(ids.get("" + index).toString());
        textFromTv = tv_name.getText() + ":" + tv_phone.getText();
        if(item.getItemId()==MENU_UPDATE){
            Intent i = new Intent(this, CreateActivity.class);
            i.putExtra("action", "update");
            i.putExtra("id", changableId);
            i.putExtra("text", textFromTv);
            startActivityForResult(i, 1);
        }else if(item.getItemId()==MENU_DELETE){
            db.delete("contacts", "_id=?", new String[]{changableId+""});
            refresh();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) refresh();
    }
}
