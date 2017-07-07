package com.example.bzuser.crudapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    Button btnSave,btnCancel;
    EditText etFirstName, etEmail;
    StudentAdapter db = new StudentAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /**
         * Insert Student Record
         */
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFirstName = (EditText) findViewById(R.id.etFirstName);
                etEmail = (EditText) findViewById(R.id.etEmail);
                db.addStudent(new ObjectStudent(etFirstName.getText().toString(), etEmail.getText().toString()));
                Toast.makeText(getApplicationContext(), "Record Successfully Inserted.", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancel=(Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
