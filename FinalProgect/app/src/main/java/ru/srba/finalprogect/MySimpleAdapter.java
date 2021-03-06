package ru.srba.finalprogect;

import android.content.Context;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {
    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    @Override
    public void setViewText(TextView v, String text) {
        if(v.getId() == R.id.roomName) {
//                Log.d("MyLogs", text);
            v.setHint(text + "1");
        }
        super.setViewText(v, text);
    }
}