package com.example.josef.passnopass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 4/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "courseManager";

    // Course table name
    private static final String TABLE_COURSE = "Course";

    // Attributes
    private static final String COURSE_DEPT = "Dept";
    private static final String COURSE_NUM = "NUM";
    private static final String COURSE_ID = COURSE_DEPT + COURSE_NUM;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + COURSE_ID + " TEXT PRIMARY KEY," + COURSE_DEPT + " TEXT,"
                + COURSE_NUM + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        // Create tables again
        onCreate(db);
    }

    public void addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COURSE_DEPT, course.getCourseDept()); // Contact Name
        values.put(COURSE_NUM, course.getCourseNum()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_COURSE, null, values);
        db.close(); // Closing database connection
    }

    // Getting single course
    public Course getCourse(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COURSE, new String[] { COURSE_ID,
                        COURSE_DEPT, COURSE_NUM }, COURSE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Course course = new Course(cursor.getString(0),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        // return contact
        return course;
    }

    // Getting All Courses
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<Course>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourseID(cursor.getString(0));
                course.setCourseDept(cursor.getString(1));
                course.setCourseNum(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        // return contact list
        return courseList;
    }

    // Getting course Count
    public int getCoursesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single course
    public int updateCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COURSE_DEPT, course.getCourseDept());
        values.put(COURSE_NUM, course.getCourseNum());

        // updating row
        return db.update(TABLE_COURSE, values, COURSE_ID + " = ?",
                new String[] { String.valueOf(course.getCourseID()) });
    }

    // Deleting single course
    public void deleteCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, COURSE_ID + " = ?",
                new String[] { String.valueOf(course.getCourseID()) });
        db.close();
    }
}
