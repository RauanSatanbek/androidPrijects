package ru.srba.task4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    final static int DB_VERSION = 1;
    final static String DB_NAME = "DB_NAME";

    public static String TABLE_NAME = "Users";
    public static String ID = "_id";
    public static String NAME = "name";
    public static String CREATE_DB = "create table " +
            TABLE_NAME + "( " +
            ID + " integer primary key autoincrement, " +
            NAME + " text)";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
