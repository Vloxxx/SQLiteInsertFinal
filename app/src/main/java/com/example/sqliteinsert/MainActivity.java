package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteinsert.Data.UserDbHelper;
import com.example.sqliteinsert.Data.UserContract.UserEntity;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass, DeleteUser, CurrentUser, NewUser;
    Button btnAdd, btnView, btnUpdate, btnDelete;
    UserDbHelper helper;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        DeleteUser = (EditText) findViewById(R.id.editText6);
        CurrentUser = (EditText) findViewById(R.id.editText3);
        NewUser = (EditText) findViewById(R.id.editText5);
        helper = new UserDbHelper(this);

        btnAdd = (Button) findViewById(R.id.button);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v);
            }
        });

        btnView = (Button) findViewById(R.id.button2);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(in);
            }
        });

        btnDelete = (Button) findViewById(R.id.button4);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(v);
            }
        });

        btnUpdate = (Button) findViewById(R.id.button3);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(v);
            }
        });

    }
    public void deleteUser(View view)
    {
            String name =  DeleteUser.getText().toString().trim();
            UserDbHelper mDbHelper = new UserDbHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            String delete = "DELETE FROM " + UserEntity.TABLE_NAME + " WHERE " + UserEntity.UID +" = " + name + ";";
            db.beginTransaction();
        try {
            db.execSQL(delete);
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void updateUser(View view)
    {
        String currentUser =  CurrentUser.getText().toString().trim();
        String newUser =  NewUser.getText().toString().trim();

        UserDbHelper mDbHelper = new UserDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //UPDATE COMPANY SET ADDRESS = 'Texas' WHERE ID = 6;
        String delete = "UPDATE " + UserEntity.TABLE_NAME + " SET " + UserEntity.USER_NAME +" = '" + newUser + "' WHERE " + UserEntity.UID+ " = " + currentUser + ";";
        db.beginTransaction();
        try {
            db.execSQL(delete);
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void addUser(View view)
    {
        Toast.makeText(this,"Running", Toast.LENGTH_LONG).show();
        String t1 = Name.getText().toString().trim();
        String t2 = Pass.getText().toString().trim();
        UserDbHelper mDbHelper = new UserDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(UserEntity.USER_NAME, t1);
        values.put(UserEntity.USER_PWD, t2);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(UserEntity.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1)
            {
                Message.message(this,"Unsuccessful");
            } else
            {
                Message.message(this,"Successful");
            }

    }

}
