package com.example.databasedemoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MY_COMAPNY.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "EMPLOYEE";
    static final String EMPLOYEE_ID = "-ID";
    static final String EMPLOYEE_NAME = "-employee_name";
    static final String EMPLOYEE_SALARY = "-employee_salary";

    private static final String CREATE_DB_QUERY = "CREATE TABLE" + DATABASE_TABLE +"("+ EMPLOYEE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            +EMPLOYEE_NAME+ "TEXT NOT NULL, " +EMPLOYEE_SALARY+ "Text not null);";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +DATABASE_TABLE);

    }
}
