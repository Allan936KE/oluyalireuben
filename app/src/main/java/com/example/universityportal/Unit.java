package com.example.universityportal;

public class Unit {

    String UnitName;
    String UnitCode;
    String LecturerName;
    String Stage;

    public Unit(String unitName, String unitCode, String lecturerName, String stage) {
        UnitName = unitName;
        UnitCode = unitCode;
        LecturerName = lecturerName;
        Stage = stage;
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
}
