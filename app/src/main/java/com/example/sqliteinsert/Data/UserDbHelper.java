package com.example.sqliteinsert.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sqliteinsert.Data.UserContract.UserEntity;
/**
 * Created by larso on 2-11-2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = UserDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "employee.db";
    private static final int DATABASE_VERSION = 1;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE "
                + UserEntity.TABLE_NAME +"( "
                + UserEntity.UID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + UserEntity.USER_NAME + " TEXT not null unique, "
                + UserEntity.USER_PWD+" TEXT not null);";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM " + UserEntity.TABLE_NAME;
        Cursor data = db.rawQuery(Query, null);
        return data;
    }
}
