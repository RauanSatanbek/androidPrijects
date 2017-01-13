package ru.srba.testelv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ExpandableListView listView;
    // названия компаний (групп)
    String[] groups = new String[] {"HTC", "Samsung"};

    // названия телефонов (элементов)
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};

    ArrayList<HashMap<String, String>> groupData;
    ArrayList<ArrayList<HashMap<String, String>>> childData;
    ArrayList<HashMap<String, String>> childDataItem;
    HashMap<String, String> m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupData = new  ArrayList<HashMap<String, String>>();
        childData = new  ArrayList<ArrayList<HashMap<String, String>>>();
        for(int i = 0; i < groups.length; i++){
            m = new  HashMap<String, String>();
            m.put("groupName", groups[i]);
            groupData.add(m);
        }

        String groupFrom[] = {"groupName"};
        int groupTo[] = {android.R.id.text1};

        childDataItem = new  ArrayList<HashMap<String, String>>();
        for(int i = 0; i < phonesHTC.length; i++){
            m = new  HashMap<String, String>();
            m.put("childName", phonesHTC[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new  ArrayList<HashMap<String, String>>();
        for(int i = 0; i < phonesSams.length; i++){
            m = new  HashMap<String, String>();
            m.put("childName", phonesSams[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = {"childName"};
        int childTo[] = {android.R.id.text1};

        listView = (ExpandableListView)findViewById(R.id.elv);
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
            this,
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo
        );

        listView.setAdapter(adapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                TextView tv = (TextView)v;
                Log.d("myLogs", tv.getText() + "");
                return false;
            }
        });
    }
}

