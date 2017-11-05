package com.example.sqliteinsert;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqliteinsert.Data.UserContract.UserEntity;
import com.example.sqliteinsert.Data.UserDbHelper;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity{

    private static final String TAG = "ListDataActivity";
    UserDbHelper mDbHelper;
    private ListView list;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        list = (ListView) findViewById(R.id.listView);
        mDbHelper = new UserDbHelper(this);
        PopulateListView();
    }

    private void PopulateListView(){
        Log.d(TAG, "populateListView: Displaying data in the listview. ");
        Cursor data = mDbHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(0));
            listData.add(data.getString(1));
            listData.add(data.getString(2));

        }
        ListAdapter ada = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        list.setAdapter(ada);
    }
}
