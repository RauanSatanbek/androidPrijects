package ru.srba.finalprogect;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Chat extends AppCompatActivity implements View.OnClickListener{
    String url ="https://rauan-android-backend.herokuapp.com/";
    EditText et_message;
    Button btn_send;
    LinearLayout messages;
    String USER_ID = "5854ba5297ba0b00043bf8c1";
    String ROOM_ID = "5854ce9759740900042c8550";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btn_send = (Button) findViewById(R.id.btn_send);
        et_message = (EditText) findViewById(R.id.et_message);
        messages = (LinearLayout) findViewById(R.id.messages);
        btn_send.setOnClickListener(this);
        new MyAsyncTask().execute();

    }

    @Override
    public void onClick(View v) {
        String message = et_message.getText().toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("roomId", ROOM_ID);
        map.put("userId", USER_ID);
        SendMessage("api/chat/add", map);
    }

    class MyAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            GetMessage("api/chat/" + ROOM_ID, null);
            return null;
        }
    }

    public void GetMessage(String urlPath, final HashMap<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(Chat.this);
        final StringRequest request = new StringRequest(
                Request.Method.GET,
                url + urlPath,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            Log.d("MyLogs", "array is: "+ array );
                            messages.removeAllViews();
                            for(int i = 0; i < array.length(); i++) {
                                JSONObject object = new JSONObject("" + array.get(i));
                                JSONArray user = new JSONArray("" + object.get("user"));
                                JSONObject userObject = new JSONObject("" + user.get(0));

                                String message = "" + object.get("message");
                                String userName = "" + userObject.get("name");

                                Log.d("MyLogs", "userObject: "+ userObject );
                                Log.d("MyLogs", "object: "+ object );
                                TextView tv1 = new TextView(Chat.this);
                                tv1.setText(userName + "\n" + message);
                                messages.addView(tv1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        request.setTag("GET");
        queue.add(request);
    }


    public void SendMessage(String urlPath, final HashMap<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(Chat.this);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url + urlPath,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MyLogs", "send message: "+ response);

                        GetMessage("api/chat/" + ROOM_ID, null);
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
        request.setTag("GET");
        queue.add(request);
    }
}
