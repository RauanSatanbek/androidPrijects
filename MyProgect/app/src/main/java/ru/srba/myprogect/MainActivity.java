package ru.srba.myprogect;

import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RadioGroup group;
    Map<String, String> answers = new HashMap<>();
    RadioButton rightRB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answers.put("A", "Lorem ipsum dolor sit amet.");
        answers.put("B", "Omnis enim nemo accusamus corporis.");
        answers.put("C", "Aut ratione reiciendis officia totam.");
        answers.put("D", "Eos unde voluptate ex soluta.");
        answers.put("E", "Maiores nemo excepturi ipsum libero.");
        answers.put("right", "A");

        ArrayList<String> variants = new ArrayList<>();
        Collections.addAll(variants, "A", "B", "C", "D", "E");
        Collections.shuffle(variants);
        group = (RadioGroup) findViewById(R.id.variants);

        for(int i = 0; i < 5; i++) {
            String variant = variants.get(i);
            String text = answers.get(variant);
            ((RadioButton)group.getChildAt(i)).setText(text);
            ((RadioButton)group.getChildAt(i)).setHint(variant);
            if(variant.equals(answers.get("right"))) {
                rightRB = (RadioButton)group.getChildAt(i);
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        String answer = ((RadioButton) view).getHint().toString();
        if(!answer.equals(answers.get("right"))) {
            Vibrator vibrator = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            ((RadioButton) view).setTextColor(Color.RED);
            rightRB.setTextColor(Color.rgb(0, 204, 0));
        } else {
            ((RadioButton) view).setTextColor(Color.rgb(0, 204, 0));
        }
    }
}
