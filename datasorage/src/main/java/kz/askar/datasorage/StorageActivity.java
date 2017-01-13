package kz.askar.datasorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StorageActivity extends AppCompatActivity {

    EditText nameET;
    TextView nameTV;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        nameET = (EditText)findViewById(R.id.nameET);
        nameTV = (TextView)findViewById(R.id.nameTV);

        sp = getSharedPreferences("sdu_week7", MODE_PRIVATE);
        String nameFromSP = sp.getString("name", "");
        nameTV.setText(nameFromSP);
    }

    public void onEnter(View v){
        String nameFromSP = sp.getString("name", "");
        String name = nameET.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", nameFromSP+","+name);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
