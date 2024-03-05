package com.example.universityportal;

public class Grade {
    String UnitName;
    String UnitCode;
    String LecturerName;
    String Stage;
    String StudentName;
    String RegNo;
    String Grade;


    public Grade(String unitName, String unitCode, String lecturerName, String stage, String studentName, String regNo, String grade) {
        UnitName = unitName;
        UnitCode = unitCode;
        LecturerName = lecturerName;
        Stage = stage;
        StudentName = studentName;
        RegNo = regNo;
        Grade = grade;

    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String lecturerName) {
        LecturerName = lecturerName;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getRegNo() {
        return RegNo;
    }

    public void setRegNo(String regNo) {
        RegNo = regNo;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
