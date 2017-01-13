package ru.srba.asynctasktesting;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    Random random;
    LinearLayout ll;
    LinearLayout.LayoutParams llp;
    int n = 10;
    Handler h;
    LinearLayout llResult;
    int count = 0;
    LinearLayout.LayoutParams llpRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout)findViewById(R.id.ll);
        llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn = (Button)findViewById(R.id.start);
        btn.setOnClickListener(this);
        random = new Random();
        llpRes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        btn.setEnabled(false);
        switch (v.getId()){
            case R.id.start:
                Start("1) Sacred Spirit: ");
                Start("2) Nabisco Cracker: ");
                Start("3) Razzle Dazzle: ");
                llResult = new LinearLayout(this);
                llResult.setOrientation(LinearLayout.VERTICAL);
                llResult.setPadding(0, 20, 0, 0);
                ll.addView(llResult, llpRes);
        }

    }
    public void Start(String name){
        TextView tv = new TextView(MainActivity.this);
        tv.setText(name);
        tv.setTextSize(20);
        tv.setPadding(30,0,0,0);
        ll.addView(tv, llp);

        SeekBar seekBar = new SeekBar(this);
        seekBar.setMax(n);
        seekBar.setProgress(2);
        ll.addView(seekBar, llpRes);

        myAsyncTask mt1 = new myAsyncTask(tv, seekBar);
//        int s = 200000 + (int)(Math.random() * 1000000);
        mt1.execute();
    }
    class myAsyncTask extends AsyncTask<Void, Integer, Void>{
        TextView tv;
        SeekBar seekBar;
        myAsyncTask(TextView tv, SeekBar seekBar) {
            this.tv = tv;
            this.seekBar = seekBar;
        }
        @Override
        protected void onPreExecute() {super.onPreExecute(); }
        @Override
        protected Void doInBackground(Void... voids) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                try {
                    for(int i = 1; i <= n; i++) {
                        publishProgress(i);
                        int ms = 200000 + (int)(Math.random() * 1000000);
                        TimeUnit.MICROSECONDS.sleep(ms);
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
            String name = tv.getText().toString().split(":")[0];
            seekBar.setProgress(values[0]);
            if(values[0] == n){
                count++;
                Log.d("myLog", name);
                TextView tv = new TextView(MainActivity.this);
                tv.setTextSize(20);
                tv.setPadding(30,0,0,0);
                tv.setText(name + " - " + count);
                llResult.addView(tv, llp);
                if(count >= 3) {
                    count = 0;
                    btn.setEnabled(true);
                }
            }
            tv.setText(name + ": " + values[0] + "/" + n);
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