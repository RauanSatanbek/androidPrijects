package ru.srba.task4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy - HH:mm:ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(date);
    }
}
