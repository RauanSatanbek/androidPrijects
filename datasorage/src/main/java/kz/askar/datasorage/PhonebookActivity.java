package kz.askar.datasorage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhonebookActivity extends AppCompatActivity {

    DBHelper dbh;
    SQLiteDatabase db;
    LinearLayout contacts;

    final int MENU_UPDATE = 1;
    final int MENU_DELETE = 2;
    long changableId = 0;
    String changableName = "";
    String changablePhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        contacts = (LinearLayout)findViewById(R.id.contacts);

        dbh = new DBHelper(this);
        db = dbh.getWritableDatabase();

        refresh(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbh.close();
    }

    public void addContact(View v){
        Intent i = new Intent(this, CreateActivity.class);
        i.putExtra("action", "create");
        startActivity(i);
    }

    public void refresh(View v){
        contacts.removeAllViews();
        LinearLayout.LayoutParams llp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Cursor c = db.query("contacts", null, null, null, null, null, "name");
        if(c.moveToFirst()){
            do{
                long id = c.getLong(c.getColumnIndex("_id"));
                String name = c.getString(c.getColumnIndex("name"));
                String phone = c.getString(c.getColumnIndex("phone"));
                TextView tv = new TextView(this);
                tv.setTextSize(30);
                tv.setText(id + ")" + name + ":" + phone);
                contacts.addView(tv, llp);
                registerForContextMenu(tv);

            }while(c.moveToNext());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU_UPDATE, 0, "Update");
        menu.add(0, MENU_DELETE, 1, "Delete");

        String contact = ((TextView)v).getText().toString();

        String idStr = contact.split("\\)")[0];
        Log.d("My Logs", idStr);
        changableId = Long.parseLong(idStr);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==MENU_UPDATE){
            Intent i = new Intent(this, CreateActivity.class);
            i.putExtra("action", "update");
            i.putExtra("id", changableId);
            startActivity(i);
        }else if(item.getItemId()==MENU_DELETE){
            db.delete("contacts", "_id=?", new String[]{changableId+""});
            refresh(null);
        }

        return super.onContextItemSelected(item);
    }
}
