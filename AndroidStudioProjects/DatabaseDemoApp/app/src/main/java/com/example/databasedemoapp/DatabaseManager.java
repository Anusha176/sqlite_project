package com.example.databasedemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context cxt){
        context = cxt;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String employeename, String employeesalary){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EMPLOYEE_NAME,employeename);
        contentValues.put(DatabaseHelper.EMPLOYEE_SALARY,employeesalary);
        database.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);


    }

    public Cursor fetch(){
        String [] columns = new String[] {DatabaseHelper.EMPLOYEE_ID,DatabaseHelper.EMPLOYEE_NAME,DatabaseHelper.EMPLOYEE_SALARY };
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns,null,null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id,String employeename,String employeesalary ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EMPLOYEE_NAME,employeename);
        contentValues.put(DatabaseHelper.EMPLOYEE_SALARY,employeesalary);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE,contentValues, DatabaseHelper.EMPLOYEE_ID + "=" +_id, null);
        return ret;
    }

    public void delete (long _id){
        database.delete(DatabaseHelper.DATABASE_TABLE,DatabaseHelper.EMPLOYEE_ID + "=" + _id, null);
    }



}
