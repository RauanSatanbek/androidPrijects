package ru.srba.move_element;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button button;
    final int alpha = 1;
    final int combo = 2;
    final int rotate = 3;
    final int scale = 4;
    final int transform = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        registerForContextMenu(tv);
        View.OnClickListener  onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button:
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                }
            }
        };
        button.setOnClickListener(onClickListener);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch(v.getId()){
            case R.id.textView:
                menu.add(0,alpha,1,"alpha");
                menu.add(0,combo,2,"combo");
                menu.add(0,rotate,3,"rotate,");
                menu.add(0,scale,4,"scale");
                menu.add(0,transform,5,"transform");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        switch (item.getItemId()){
            case alpha:
                anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                break;
            case combo:
                anim = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;
            case rotate:
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case scale:
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case transform:
                anim = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;

        }
        tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }
}
