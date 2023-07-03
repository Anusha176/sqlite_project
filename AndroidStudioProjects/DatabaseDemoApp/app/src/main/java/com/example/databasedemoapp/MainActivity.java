package com.example.databasedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextid;
    EditText editTextname;
    EditText editTextsalary;

    Button btn_insert, btn_fetch, btn_update, btn_delete;
    DatabaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextid = findViewById(R.id.editTextid);
        editTextname = findViewById(R.id.editTextname);
        editTextsalary = findViewById(R.id.editTextsalary);
        btn_insert = findViewById(R.id.btn_insert);
        btn_fetch = findViewById(R.id.btn_fetch);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbManager.insert(editTextname.getText().toString(), editTextsalary.getText().toString());

            }
        });
        btn_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbManager.fetch();
                if (cursor.moveToFirst()) {
                    do {
                        //String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_ID));
                        @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_ID));
                        @SuppressLint("Range") String employeename = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_NAME));
                        @SuppressLint("Range") String employeesalary = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMPLOYEE_SALARY));
                        Log.i("DATABASE_TAG", "I have read ID: " + ID + "username: " + employeename + "salary:" + employeesalary);

                    } while (cursor.moveToNext());
                }

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.update(Long.parseLong(editTextid.getText().toString()), editTextname.getText().toString(), editTextsalary.getText().toString());


            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.delete(Long.parseLong(editTextid.getText().toString()));


            }
        });


    }


}

