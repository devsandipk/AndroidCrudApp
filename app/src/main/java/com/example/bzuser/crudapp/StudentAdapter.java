package com.example.bzuser.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bzuser on 7/7/17.
 */
public class StudentAdapter {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SchoolManagement";

    private static final String TABLE_STUDENT = "Student";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_EMAIL = "email";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                    + KEY_ROWID + " INTEGER PRIMARY KEY," + KEY_FIRSTNAME + " TEXT,"
                    + KEY_EMAIL + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

            // Create tables again
            onCreate(db);
        }
    }

    public StudentAdapter(Context ctx) {
        this.mCtx = ctx;
    }



    /**
     * add Student to SQLite Db.
     *
     * @param student
     */
    public void addStudent(ObjectStudent student) {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student.getFirstname());
        values.put(KEY_EMAIL, student.getEmail());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    /**
     * get particular student
     *
     * @param id
     * @return
     */
    public ObjectStudent getStudents(int id) {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{KEY_ROWID, KEY_FIRSTNAME, KEY_EMAIL}, KEY_ROWID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ObjectStudent student = new ObjectStudent(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return student;
    }

    /**
     * get Total Number of Student Count
     *
     * @return
     */
    public int getStudentCount() {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        String countQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Update student record
     *
     * @param student
     * @return
     */
    public int updateStudent(ObjectStudent student) {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student.getFirstname());
        values.put(KEY_EMAIL, student.getEmail());

        return db.update(TABLE_STUDENT, values, KEY_ROWID + "=?", new String[]{String.valueOf(student.getId())});

    }

    /**
     * get All Student
     *
     * @return
     */
    public List<ObjectStudent> getAllStudents() {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        List<ObjectStudent> studentlist = new ArrayList<ObjectStudent>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ObjectStudent student = new ObjectStudent();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setFirstname(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                studentlist.add(student);
            } while (cursor.moveToNext());
        }

        return studentlist;
    }

    /**
     * Fetch All Student;
     *
     * @return
     */
    public Cursor fetchAllStudent() {

        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[] {KEY_ROWID,
                        KEY_FIRSTNAME, KEY_EMAIL},
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * delete Student
     *
     * @param student
     */
    public void deleteStudent(ObjectStudent student) {
        DatabaseHelper mDbHelper=new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ROWID + "=?", new String[]{String.valueOf(student.getId())});
        db.close();
    }

}
