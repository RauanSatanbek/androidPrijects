package ru.srba.qwerty;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ExpandableListView elv;
    myAsyncTask myTask;
    ArrayList<HashMap<String, String>> parent;
    HashMap<String, String> m;
    ArrayList<ArrayList<HashMap<String, String>>> child;
    ArrayList<HashMap<String, String>> childData;
    SimpleExpandableListAdapter adapter;
    int counter[][];
    int count = 0;
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elv = (ExpandableListView)findViewById(R.id.elv);
        r = new Random();

        counter = new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        // parent
        parent = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            m = new HashMap<>();
            m.put("parent", "Parent " + i);
            parent.add(m);
        }
        String fromP[] = {"parent"};
        int toP[] = {android.R.id.text1};
        //child
        child = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            childData = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                m = new HashMap<>();
                m.put("child", "Child " + i + " " + j +":" + count);
                childData.add(m);
            }
            child.add(childData);
        }
        String fromC[] = {"child"};
        int toC[] = {android.R.id.text1};
        adapter = new SimpleExpandableListAdapter(
                this,
                parent,
                android.R.layout.simple_expandable_list_item_1,
                fromP,
                toP,
                child,
                android.R.layout.simple_list_item_1,
                fromC,
                toC
        );

        elv.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d("myLogs", "Expanded");
                myAsyncTask mt = new myAsyncTask();
                mt.execute(groupPosition);

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    class myAsyncTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected void onPreExecute() {super.onPreExecute(); }
        @Override
        protected Void doInBackground(final Integer... pos) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(int i = 0; i < 3; i++) {
                            TimeUnit.SECONDS.sleep(1);
                            publishProgress(pos[0]);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            for(int j = 0; j < 4; j++){
                count = r.nextInt(6) + 1;
                counter[values[0]][j] = counter[values[0]][j] + count;
                child.get(values[0]).get(j).put("child", "Child " + values[0] + " " + j +":" + counter[values[0]][j]);
                adapter.notifyDataSetChanged();
            }
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        private void downloadFile(String url) throws InterruptedException{
            TimeUnit.SECONDS.sleep(2);

        }
    }
}
