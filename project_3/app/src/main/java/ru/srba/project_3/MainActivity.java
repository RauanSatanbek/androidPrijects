package ru.srba.project_3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.change);
        tv1 = (TextView)findViewById(R.id.tenge);
        tv2 = (TextView)findViewById(R.id.dollar);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(v.getId() == R.id.change){
                    String s = "" + tv1.getText();
//                    double dollar = Double.parseDouble(s);
                    tv2.setText("" + (Double.parseDouble(s) / 339.50));
                }
            }
        };
        btn.setOnClickListener(onClickListener);
    }
}
