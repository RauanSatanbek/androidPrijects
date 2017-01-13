package ru.srba.testing_all;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv;
    RelativeLayout rl;
    Button btn;
    LinearLayout ll;
    final int _RED = 1, _GREEN = 2, _BLUE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        rl = new RelativeLayout(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.
                MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(rl, lp);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.
                WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn = new Button(this);
        btn.setText("Button");
        btn.setBackgroundColor(Color.GRAY);
        btn.setTextColor(Color.WHITE);
        btn.setId(R.id.my_button_1);
        btn.setOnClickListener(this);
        rl.addView(btn, lp2);
    }
    @Override
    public void onClick(View v) {
        Animation anim = null;
        switch (v.getId()){
            case R.id.my_button_1:
//                anim = new AnimationUtils().loadAnimation()
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_red:
                rl.setBackgroundColor(Color.RED);
                break;
            case R.id.item_green:
                rl.setBackgroundColor(Color.GREEN);
                break;
            case R.id.item_blue:
                rl.setBackgroundColor(Color.BLUE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        switch (v.getId()){
//            case R.id.textView:
//                menu.add(0,4,1,"alpha");
//                menu.add(0,5,1,"scale");
//                menu.add(0,6,1,"rotate");
//                menu.add(0,7,1,"translate");
//                menu.add(0,8,1,"combo");
//                break;
//        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        Animation anim = null;
//        switch (item.getItemId()) {
//            case 4:
//                anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
//                tv.startAnimation(anim);
//                break;
//            case 5:
//                anim = AnimationUtils.loadAnimation(this, R.anim.scale);
//                tv.startAnimation(anim);
//                break;
//            case 6:
//                anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
//                tv.startAnimation(anim);
//                break;
//            case 7:
//                anim = AnimationUtils.loadAnimation(this, R.anim.translate);
//                tv.startAnimation(anim);
//                break;
//            case 8:
//                anim = AnimationUtils.loadAnimation(this, R.anim.combo);
//                tv.startAnimation(anim);
//                break;
//        }
        return super.onContextItemSelected(item);
    }
}
