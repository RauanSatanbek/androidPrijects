package ru.srba.finalprogect;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Рауан on 01.12.2016.
 */

public class ConnectionToServer {
    String url ="https://rauan-android-backend.herokuapp.com";
    Context context;
    String response = "";
    ConnectionToServer(Context context){
        this.context = context;
    }
    public String Connect(String urlPath, final HashMap<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
            Request.Method.GET,
            url + urlPath,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response2) {
                    response = response2;
                    Log.d("MyLogs", "Response is: "+ response2);
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
       return response;
    }
}
