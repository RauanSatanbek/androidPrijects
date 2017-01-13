package ru.srba.test_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.MalformedInputException;

public class DBHelper extends SQLiteOpenHelper{

    final static String DB_NAME = "mydb";
    final static int DB_VERSION = 1;


    final static String ID = "_id";
    final static String NAME = "name";
    final static String TABLE_NAME = "mail";
    final static String CREATE_TABLE = "create table " + TABLE_NAME + "( "+
            ID + " integer primary key autoincrement, " +
            NAME + " text )";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
