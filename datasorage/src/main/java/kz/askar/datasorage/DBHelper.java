package kz.askar.datasorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zhakenov on 15.10.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context ctx){
        super(ctx, "phonebook", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts(" +
                    "_id integer primary key AUTOINCREMENT," +
                    "name text," +
                    "phone text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
