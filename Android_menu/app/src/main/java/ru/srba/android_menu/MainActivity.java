package ru.srba.android_menu;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4;
    RelativeLayout rl;
    MenuItem i1, i2, i3;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        rl = (RelativeLayout)findViewById(R.id.rl);
        rl.setBackgroundColor(Color.RED);
        menu = (Menu)findViewById(R.id.menu);
        registerForContextMenu(textView);
        registerForContextMenu(textView2);
        registerForContextMenu(textView3);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        textView4 = (TextView) v;
        switch (v.getId()){
            case R.id.textView:
                menu.add(0,1,1,"red");
                menu.add(0,2,2,"blue");
                menu.add(0,3,3,"green");
                break;
            case R.id.textView2:
                menu.add(0,1,1,"red");
                menu.add(0,2,2,"blue");
                menu.add(0,3,3,"green");
                break;
            case R.id.textView3:
                menu.add(0,1,1,"red");
                menu.add(0,2,2,"blue");
                menu.add(0,3,3,"green");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        Log.d("My Logs", item.getMenuInfo().toString());
        switch (item.getItemId()){

            case 1:
                textView4.setTextColor(Color.RED);
                break;
            case 2:
                textView4.setTextColor(Color.BLUE);
                break;
            case 3:
                textView4.setTextColor(Color.GREEN);
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        item.setVisible(false);
       switch(id){
           case R.id.red:
               i2.setVisible(true);
               i3.setVisible(true);
               rl.setBackgroundColor(Color.RED);
               break;
           case R.id.blue:
               i1.setVisible(true);
               i3.setVisible(true);
               rl.setBackgroundColor(Color.BLUE);
               break;
           case R.id.green:
               i2.setVisible(true);
               i1.setVisible(true);
               rl.setBackgroundColor(Color.GREEN);
               break;
       }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        i1 = menu.findItem(R.id.red);
        i2 = menu.findItem(R.id.blue);
        i3 = menu.findItem(R.id.green);
        return super.onPrepareOptionsMenu(menu);
    }
}