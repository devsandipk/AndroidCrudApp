package com.example.bzuser.crudapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddNew;
    StudentAdapter db = new StudentAdapter(this);
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The desired columns to be bound
        String[] columns = new String[]{
                db.KEY_FIRSTNAME,
                db.KEY_EMAIL
        };

// the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.tvFirstName,
                R.id.tvEmail
        };

        Cursor cursor=db.fetchAllStudent();

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.student,cursor,columns,to,0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);


        btnAddNew = (Button) findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }
}
