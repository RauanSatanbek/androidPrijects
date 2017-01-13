package sdu.margulan.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView main_sc;
    private String display="";
    private String operator="";
    private String a="";
    private String b="";
    private String d_operator="";
    private boolean op=false;
    private boolean bool = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        main_sc = (TextView)findViewById(R.id.textView);
        main_sc.setText(display);
    }

    public void updScreen(){
        main_sc.setText(display);
    }

    public void onClickNumber(View v){
        if (bool == true){
            display="";
            b="";
            updScreen();
            bool = false;
        }
        Button b = (Button) v;
        if(op){
            display = (String) b.getText();
            op=false;
        }
        else {
            display += b.getText();
        }
        updScreen();
    }

    public void onClickOperator(View v){
        Button b = (Button) v;
        operator = b.getText().toString();
        a = display;

        op=true;
        updScreen();
    }

    private double Esepteu(String a, String b, String operator){
        updScreen();
        switch (operator){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "/": return Double.valueOf(a) / Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            default: return 0;
        }
    }
    public void onClickEqual(View v){
        Button bt = (Button) v;
        b = display;
        Double result = Esepteu(a,b,operator);
        if(Math.round(result)==result) display=Math.round(result)+"" ;
        else display = result+"";
        updScreen();
        bool = true;
    }

    public void onClickDarezhe (View v){
        Button bt= (Button) v;
        d_operator = bt.getText().toString();
        double result;
        switch (d_operator){
            case "x^2": result = Math.pow(Double.valueOf(display),2); break;
            case "x^3": result = Math.pow(Double.valueOf(display),3); break;
            default: return;
        }
        if(Math.round(result)==result) display=Math.round(result)+"" ;
        else display = result+"";
        a=display;
        updScreen();
    }

    public void clear(){
        display="";
        operator="";
        updScreen();
    }

    public void onClickAC(View v){
        clear();
        updScreen();
    }
}
