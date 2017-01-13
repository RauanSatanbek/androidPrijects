package ru.srba.expandablelistteszt;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int n = 10;
    static Set<Integer> ids;
    int count = -1;
    ArrayList<HashMap<String, String>> m;
    ListView listView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> map;
        m = new ArrayList<HashMap<String, String>>();
        ids = new HashSet<>();
        listView = (ListView)findViewById(R.id.listView);
        for (int i = 0; i < n; i++){
            map = new HashMap<String, String>();
            map.put("name", "Activity " + (i + 1));
            m.add(map);
        }
        setListView(m);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.RED);
                ids.add(position);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name", ((TextView)view).getText());
                startActivityForResult(intent, 1);
                Toast.makeText(MainActivity.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void setListView(ArrayList<HashMap<String, String>> m){
        SimpleAdapter adapter = new SimpleAdapter(this, m, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
//        adapter.setViewBinder(new MyViewBinder());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Log.d("myLogs", ids.toString());
        }
    }

}
