package ru.srba.myapplication;

import android.os.Build;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Рауан on 25.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(time);
    }
}
