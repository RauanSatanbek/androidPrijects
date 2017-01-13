package ru.srba.test_intent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Contact";
    public static final String TABLE_CONTACTS = "Contacts";

    public static final String Key_id = "_id";
    public static final String Key_name = "name";
    public static final String Key_mail = "mail";

    private static  String CREATE_DB = "create table "
            + TABLE_CONTACTS + "( "
            + Key_id + "integer primary kay autoincrement, "
            + Key_name + " text, "
            + Key_mail + " text, )";

    public DBHelper(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_DB); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
