package ru.srba.connectiontesting;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

public class reg extends AppCompatActivity implements View.OnClickListener{
    String urlStr = "https://rauan-android-backend.herokuapp.com/api/reg";
    EditText name, login, password;
    TextView tv_error;
    Button btn_ok;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        tv_error = (TextView) findViewById(R.id.tv_error);
        name = (EditText) findViewById(R.id.name);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nameStr = name.getText().toString();
        String loginStr = login.getText().toString();
        String passwordStr = password.getText().toString();

        if(nameStr.equals("") || loginStr.equals("") || passwordStr.equals("")) {
            tv_error.setText("Заполните все поля");
        } else {
            tv_error.setText("");
            new MyRegAsync().execute(urlStr, nameStr, loginStr, passwordStr);
        }
    }


    class MyRegAsync extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                String urlParameters = "name=" + params[1] + "&login=" + params[2] + "&password=" + params[3];

                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(urlParameters);
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while((line = reader.readLine()) != null) {
                    result += line;
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            Log.d("MyLogs", s);
        }
    }
}
