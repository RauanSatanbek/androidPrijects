package ru.srba.culculator;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    static TextView editText, results;
    static Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, over, plus, minus, times, clear, equeals, bktOpen2, bktClose2, remove;
    int num, i;
    int m[] = {1,2,3,4};
    static int bktOpen = 0, bktClose = 0;
    static boolean bool = true;
    static int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.editText);
        results = (TextView) findViewById(R.id.results);
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        over = (Button) findViewById(R.id.over);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        times = (Button) findViewById(R.id.times);
        clear = (Button) findViewById(R.id.clear);
        equeals = (Button) findViewById(R.id.equeals);
        bktOpen2 = (Button) findViewById(R.id.bktOpen);
        bktClose2 = (Button) findViewById(R.id.bktClose);
        remove = (Button) findViewById(R.id.remove);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.b0: editText.setText(editText.getText() + "" + 0);break;
                    case R.id.b1: editText.setText(editText.getText() + "" + 1);break;
                    case R.id.b2: editText.setText(editText.getText() + "" + 2);break;
                    case R.id.b3: editText.setText(editText.getText() + "" + 3);break;
                    case R.id.b4: editText.setText(editText.getText() + "" + 4);break;
                    case R.id.b5: editText.setText(editText.getText() + "" + 5);break;
                    case R.id.b6: editText.setText(editText.getText() + "" + 6);break;
                    case R.id.b7: editText.setText(editText.getText() + "" + 7);break;
                    case R.id.b8: editText.setText(editText.getText() + "" + 8);break;
                    case R.id.b9: editText.setText(editText.getText() + "" + 9);break;
                    case R.id.bktOpen: editText.setText(editText.getText() + "(");break;
                    case R.id.bktClose: editText.setText(editText.getText() + ")");break;
                    case R.id.plus: editText.setText(editText.getText() + "+");break;
                    case R.id.minus: editText.setText(editText.getText() + "-");break;
                    case R.id.over: editText.setText(editText.getText() + "/");break;
                    case R.id.times: editText.setText(editText.getText() + "*");break;
                    case R.id.clear: editText.setText("");break;
                    case R.id.remove: editText.setText((editText.getText() + "").substring(0, (editText.getText() + "").length()-1));break;
                    case R.id.equeals:
                        results.setText(editText.getText());
                        ResultPlusAndMinus(ResultTimesAndOver(count("" + editText.getText())));
                        break;
                }
            }
        };

        b0.setOnClickListener(onClickListener);
        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
        b3.setOnClickListener(onClickListener);
        b4.setOnClickListener(onClickListener);
        b5.setOnClickListener(onClickListener);
        b6.setOnClickListener(onClickListener);
        b7.setOnClickListener(onClickListener);
        b8.setOnClickListener(onClickListener);
        b9.setOnClickListener(onClickListener);
        clear.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);
        over.setOnClickListener(onClickListener);
        times.setOnClickListener(onClickListener);
        equeals.setOnClickListener(onClickListener);
        bktOpen2.setOnClickListener(onClickListener);
        bktClose2.setOnClickListener(onClickListener);
        remove.setOnClickListener(onClickListener);
    }

    public static String ResultTimesAndOver(String s){
        String num = "";
        boolean bool = false;
        for(int i = 0; i < s.length(); i++){
            if((int)s.charAt(i) == (int)'*' || (int)s.charAt(i) == (int)'/'){
                int nInt = NextInt(s, i);
                int pInt = PrevInt(s, i);
                if((int)s.charAt(i) == (int)'*')
                    num = s.substring(0, i - ("" + pInt).length()) + (nInt * pInt) + s.substring(i + ("" + nInt).length() + 1);
                else num = s.substring(0, i - ("" + pInt).length()) + (pInt / nInt) + s.substring(i + ("" + nInt).length() + 1);
                bool = true;
                break;
            }
        }
        if(!bool) return s;
//        System.out.println(num);
        results.setText(results.getText() + "\n"+ num );
        return ResultTimesAndOver(num);
    }
    public static String ResultPlusAndMinus(String s){
        String num = "";
        boolean bool = false;
        for(int i = s.length() - 1; i > -1; i--){
            if((int)s.charAt(i) == (int)'+' || (int)s.charAt(i) == (int)'-' && s.length() > 2){
                int nInt = NextInt(s, i);
                int pInt = PrevInt(s, i);
                if((int)s.charAt(i) == (int)'+')
                    num = s.substring(0, i - ("" + pInt).length()) + (nInt + pInt) + s.substring(i + ("" + nInt).length() + 1);
                else num = s.substring(0, i - ("" + pInt).length()) + (pInt - nInt) + s.substring(i + ("" + nInt).length() + 1);
                bool = true;
                break;
            }
        }
        if(!bool) return s;
//        System.out.println(num);
        results.setText(results.getText() + "\n"+ num );
        return ResultPlusAndMinus(num);
    }

    public static String Reverse(String s){
        String num = "";
        for(int i = s.length() - 1; i > -1; i--){
            num += s.charAt(i);
        }
        return num;
    }
    public static int NextInt(String s, int index){
        String num = "";
        for(int i = index + 1; i < s.length(); i++) {
            if ((int) s.charAt(i) >= (int) '0' && (int) s.charAt(i) <= (int) '9') {
                num += s.charAt(i);
            }else break;
        }
        return Integer.parseInt(num);
    }
    public static int PrevInt(String s, int index){
        String num = "";
        for(int i = index - 1; i >= 0; i--) {
            if ((int) s.charAt(i) >= (int) '0' && (int) s.charAt(i) <= (int) '9') {
                num += s.charAt(i);
            }else break;
        }
        return Integer.parseInt(Reverse(num));
    }
    public static int calculate(String s){
        int m[] = new int[2];
        int c = 0;
        String num = "";
        for(int i = 0; i < s.length(); i++){
            if((int)s.charAt(i) >= (int)'0' && (int)s.charAt(i) <= (int)'9' ){
                num += s.charAt(i);
            } else {
                if((int)s.charAt(i) == (int)'+') value = 0;
                else if((int)s.charAt(i) == (int)'-') value = 1;
                else if((int)s.charAt(i) == (int)'*') value = 2;
                else if((int)s.charAt(i) == (int)'/') value = 3;
                m[c] = Integer.parseInt(num);
                num = "";
                c++;
            }
            if(i + 1 == s.length())m[c] = Integer.parseInt(num);
        }
        if(value == 0) return m[0] + m[1];
        else if(value == 1) return m[0] - m[1];
        else if(value == 2) return m[0] * m[1];
        return m[0] / m[1];
    }

    public static String count(String s){
        ArrayList<Integer> index = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if((int)s.charAt(i) == (int)'(') {bktOpen = i + 1;}
            if((int)s.charAt(i) == (int)')') {
                bktClose = i;
                index.add(bktOpen);
                index.add(bktClose);
                break;
            }
        }

        if(index.size() == 0) return s;
        String calculate = s.substring(index.get(0), index.get(1));
        s =  s.substring(0,index.get(0) - 1) + "" + calculate(calculate) + s.substring(index.get(1) + 1);
//        System.out.println(s);
        results.setText(results.getText() + "\n"+ s );
        return count(s);
    }
}