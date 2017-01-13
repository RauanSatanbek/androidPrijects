package ru.srba.testingandroid;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    RadioGroup rGroup;
    LinearLayout lLayout;
    TextView name;
    Button btnCreate, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rGroup = (RadioGroup)findViewById(R.id.rGroup);
        lLayout = (LinearLayout)findViewById(R.id.llayout);
        name = (TextView)findViewById(R.id.name);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnClear = (Button)findViewById(R.id.btnClear);

        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreate:
                LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT ,LinearLayout.LayoutParams.WRAP_CONTENT);
                Button btn = new Button(this);
                btn.setText("Button");
                switch(rGroup.getCheckedRadioButtonId()){
                    case R.id.Left:
                        ll.gravity = Gravity.LEFT;
                        break;
                    case R.id.Right:
                        ll.gravity = Gravity.RIGHT;
                        break;
                    case R.id.Center:
                        ll.gravity = Gravity.CENTER_HORIZONTAL;
                        break;
                }
                lLayout.addView(btn, ll);
                break;
            case R.id.btnClear:
                lLayout.removeAllViews();
                break;
        }
    }
}
