package ru.srba.connectiontesting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class checking extends AppCompatActivity implements View.OnClickListener{

    String urlStr = "https://rauan-android-backend.herokuapp.com/api/check";
    ProgressBar progressBar;
    EditText login, password;
    Button send;
    TextView err;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        err = (TextView)findViewById(R.id.err);
    }

    @Override
    public void onClick(View v) {
        err.setText("");
        String loginStr = login.getText().toString();
        String passwordStr = password.getText().toString();
        new ConnectionAT().execute(urlStr, loginStr, passwordStr);
    }

    class ConnectionAT extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                String urlParameters = "login=" + strings[1] + "&password=" + strings[2];
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(urlParameters);
                writer.flush();

                String line;
                String result = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null) {
                    result += line;
                }
                writer.close();
                reader.close();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... result) {
            super.onProgressUpdate(result);
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("MyLogs", result);
            try {
                if(result.equals("0")) {
                    err.setText("неправильный логин или пароль");
                } else {
                    JSONObject obj = new JSONObject(result);
                    Intent intent = new Intent();
                    intent.putExtra("name", obj.getString("name"));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(result);
        }
    }
}
