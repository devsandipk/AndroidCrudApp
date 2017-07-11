package com.example.bzuser.crudapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Button btnSave, btnCancel, btnUpdate, btnDelete;
    TextView tvSid;
    EditText etFirstName, etEmail;
    StudentAdapter db = new StudentAdapter(this);
    ObjectStudent student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String SID = getIntent().getStringExtra("SID");


        tvSid = (TextView) findViewById(R.id.hidden_value);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        tvSid.setText(SID);

        if (!SID.equals("0")) {
            student = db.getStudents(Integer.parseInt(SID));
            etFirstName.setText(student.getFirstname());
            etEmail.setText(student.getEmail());

            btnSave.setVisibility(View.GONE);
        } else {
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }

        /**
         * Insert Student Record
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    db.addStudent(new ObjectStudent(etFirstName.getText().toString(), etEmail.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Record Successfully Inserted.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    student.setFirstname(etFirstName.getText().toString());
                    student.setEmail(etEmail.getText().toString());
                    db.updateStudent(student);
                        Toast.makeText(getApplicationContext(), "Record Successfully Updated.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setCancelable(true);
                builder.setTitle("Delete?");
                builder.setMessage("Are you sure want to delete " + student.getFirstname() + " ?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteStudent(student);
                                Toast.makeText(getApplicationContext(), "Record Successfully Deleted.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean checkValidation() {

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        if (etFirstName.getText().toString().equals("")) {
            etFirstName.setError("Please enter value for firstname");
            return false;
        }

        etEmail = (EditText) findViewById(R.id.etEmail);
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("Please enter value for email");
            return false;
        }

        return true;
    }
}
