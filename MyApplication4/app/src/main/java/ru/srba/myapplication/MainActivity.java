package ru.srba.myapplication;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout rl;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        rl = new RelativeLayout(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.
                MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(rl, lp);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.
                WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(140, 80);
        btn = new Button(this);
        btn.setText("Button");
        btn.setBackgroundColor(Color.GRAY);
        btn.setTextColor(Color.WHITE);
        btn.setId(R.id.my_button_1);

        btn.setOnClickListener(this);
        rl.addView(btn, lp2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void RemoveRules(RelativeLayout.LayoutParams rlp){
        rlp.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
        rlp.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
        rlp.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlp.removeRule(RelativeLayout.CENTER_IN_PARENT);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout.LayoutParams lp22 = (RelativeLayout.LayoutParams)btn.getLayoutParams();
        RemoveRules(lp22);
        switch (item.getItemId()){
            case R.id.item_top_left:
                lp22.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp22.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case R.id.item_top_right:
                lp22.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp22.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case R.id.item_bottom_left:
                lp22.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp22.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case R.id.item_bottom_right:
                lp22.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp22.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case R.id.center:
                lp22.addRule(RelativeLayout.CENTER_IN_PARENT);
                break;
        }
        btn.setLayoutParams(lp22);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation);
        btn.startAnimation(anim);
//        RelativeLayout.LayoutParams lp22 = (RelativeLayout.LayoutParams)btn.getLayoutParams();
//        RemoveRules(lp22);
//        lp22.addRule(RelativeLayout.CENTER_IN_PARENT);
//        btn.setLayoutParams(lp22);
    }
}
//Task3-android
