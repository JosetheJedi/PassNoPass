package com.example.josef.passnopass;

/**
 * Created by Josef on 4/23/2017.
 */

public class Course {

    String courseDept = null;
    int courseNum = 0;
    String courseID = null;

    public Course(){

    }

    public Course(String courseID, String courseDept, int courseNum){
        this.courseDept = courseDept;
        this.courseNum = courseNum;
        this.courseID = courseID;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

}
