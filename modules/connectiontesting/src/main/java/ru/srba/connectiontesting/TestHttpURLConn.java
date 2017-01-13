package ru.srba.connectiontesting;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

public class TestHttpURLConn extends AppCompatActivity implements View.OnClickListener{
    ProgressBar progressBar3;
//    String urlStr = "https://telegrambot.kz/quiz/";
    String urlStr = "https://rauan-android-backend.herokuapp.com/api/quiz";
    ArrayList<String> array;
    ListView listView;
    ArrayAdapter adapter;
    int start = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http_urlconn);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.listView);
        array = new ArrayList<>();
//        myAdapter adapter = new myAdapter(this, android.R.layout.simple_list_item_1, array);
        new ConnectionAT2().execute(urlStr, "" + start);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("MyLogs", firstVisibleItem + 10+ " - " + visibleItemCount + " - " + totalItemCount);
                if((firstVisibleItem + 10) % 20 == 0) {
                    start += 20;
                    if(start < 100) new ConnectionAT2().execute(urlStr, "" + start);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
    class ConnectionAT2 extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute()
        {
            progressBar3.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                String urlParams = "what=Item&start=" + strings[1];
//                String urlParams = "login=rauan&password=123456";
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(urlParams);
                writer.flush();

                String line;
                String result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null) {
                    result += line;
                }
//                writer.close();
                reader.close();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(String... result) {
            super.onProgressUpdate(result);
        }
        @Override
        protected void onPostExecute(String result) {
            progressBar3.setVisibility(View.GONE);
            try {
//                JSONObject Jsonarray = new JSONObject(result);
                Intent intent = new Intent();
                JSONArray Jsonarray = new JSONArray(result);
                for(int i = 0; i < Jsonarray.length(); i++) {
                    array.add(Jsonarray.getJSONObject(i).getString("element"));
                }
                adapter.notifyDataSetChanged();
                Log.d("MyLogs", array.toString());
//                intent.putExtra("name", obj.getString("name"));
//                setResult(RESULT_OK, intent);
//                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }


    class myAdapter extends BaseAdapter{
        Context ctx;
        LayoutInflater lInflater;
        ArrayList<String> objects;
        myAdapter(Context context, int simple_list_item_1, ArrayList<String> products) {
            ctx = context;
            objects = products;
//            lInflater = (LayoutInflater) ctx
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
