package ru.srba.finalprogect;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    String url ="https://rauan-android-backend.herokuapp.com/";
    TextView tv;
    ListView listView;
    ArrayList<HashMap<String, String>> data;
    MySimpleAdapter adapter;
    int count = 0;
    final int LOGIN = 1, CREATEROOM = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        startActivityForResult(intent, LOGIN);
        tv = (TextView) findViewById(R.id.tv);

        listView = (ListView)findViewById(R.id.listView);
        data = new ArrayList<>();
        HashMap<String, String> map;
        for(int i = 0; i < 10; i++) {
            map = new HashMap<>();
            map.put("roomName", "Room " + (i + 1));
            map.put("creatorName", "Creator Name  " + (i + 1));
            map.put("number", (i + 1) + " / 10");
            data.add(map);
        }
        adapter = new MySimpleAdapter(
            this,
            data,
            R.layout.item,
            new String[]{"roomName", "creatorName", "number"},
            new int[]{R.id.roomName, R.id.creatorName, R.id.number}
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.roomName);
                count++;
//                Log.d("MyLogs", v.getHint().toString());

                Intent intent = new Intent(MainActivity.this, Chat.class);
                startActivity(intent);
//                data.get(0).put("number", count + " / 10");
//                data.removeAll(data);
//                HashMap<String, String> map;
//                for(int i = 0; i < 10; i++) {
//                    map = new HashMap<>();
//                    map.put("roomName", "Room2 " + (i + 1));
//                    map.put("creatorName", "Creator Name2  " + (i + 1));
//                    map.put("number", (i + 1) + " / 20");
//                    data.add(map);
//                }
//
//                adapter.notifyDataSetChanged();
            }
        });
        new MyAsyncTask().execute();
//        Connect();
    }

    class MyAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            Connect("api/room", null);
            return null;
        }
    }
    public void Connect(String urlPath, final HashMap<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url + urlPath,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MyLogs", "Response is: "+ response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MyLogs", error.toString());
                    }
                }){
            protected HashMap<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setTag("POST");
        queue.add(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MyLogs", "onActivityResult");
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOGIN:
                    ConnectionToServer connectionToServer = new ConnectionToServer(MainActivity.this);
                    Log.d("MyLogs", "ConnectionToServer" + connectionToServer.Connect("/", null));
                    tv.setText(data.getStringExtra("name"));
                    break;
                case CREATEROOM:
                    Log.d("MyLogs", data.toString());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.create:
                Intent intent = new Intent(MainActivity.this, CreateRoom.class);
                startActivityForResult(intent, CREATEROOM);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
