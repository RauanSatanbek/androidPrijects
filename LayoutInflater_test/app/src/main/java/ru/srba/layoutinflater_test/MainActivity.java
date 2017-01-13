package ru.srba.layoutinflater_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
                "Костя", "Игорь", "Анна", "Денис", "Андрей" };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        android.R.layout.lis
//        LinearLayout activity_main = (LinearLayout)findViewById(R.id.activity_main);
//        LayoutInflater inflater = getLayoutInflater();
//        inflater.inflate(R.layout.text, activity_main, true);
        ListView lv = (ListView)findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, names);
        lv.setAdapter(adapter);
    }
}
