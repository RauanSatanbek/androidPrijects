package ru.srba.volleytest;


import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = "";
        int n = scn.nextInt();
        int m[] = new int[n];
        m[0] = scn.nextInt();
        str +=  m[0] + " ";
        boolean bool = false;
        for(int i = 1; i < n; i++) {
            m[i] = scn.nextInt();
            str +=  m[i] + " ";
            if(m[i - 1] > m[i]) {
                bool = true;
            }
        }
        int l = m[n - 1];
        boolean bool3 = false;
        for(int i = n - 2; i > -1; i--) {
           if(l > m[i]) {
               bool3 = true;
           }
           l = m[i];
        }
        if(!bool) System.out.println("yes\n1 1");
        else if(!bool3) System.out.println("yes\n1 " + n);
        else {
            int f = m[0];
            int index = -1;
            int index2 = -1;
            boolean bool2 = false;
            for(int i = 1; i < n; i++) {
                if(f > m[i] && index == -1) {
                    index = i;
                } else if(f < m[i] && index2 == -1 && index != -1) {
                    index2 = i;
                } else if(f > m[i] && index != -1 && index2 != -1){
                    bool2 = true;
                    break;
                }
                f = m[i];
            }
            if(!bool2 && m[index - 1] < m[index2]) System.out.println("yes\n" + index + " " + index2);
            else System.out.println("no");
        }
    }
}


