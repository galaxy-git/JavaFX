package com.example.javafx.source.doctor.proj;

import java.io.Serializable;

public class Doctor implements Serializable {
    //医生编号
    private String doctor_number;
    //医生姓名
    private String doctor_name;
    //性别
    private String doctor_sex;
    //所属科室
    private String doctor_department;
    //电话号码
    private String doctor_phone;

    public Doctor(){}
    public Doctor(String doctor_number,String doctor_name,String doctor_sex,String doctor_department,String doctor_phone){
        this.doctor_number=doctor_number;
        this.doctor_name=doctor_name;
        this.doctor_sex=doctor_sex;
        this.doctor_department=doctor_department;
        this.doctor_phone=doctor_phone;
    }

    public String getDoctor_number() {
        return doctor_number;
    }
    public String getDoctor_name() {
        return doctor_name;
    }
    public String getDoctor_sex() {
        return doctor_sex;
    }
    public String getDoctor_department() {
        return doctor_department;
    }
    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }
    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }
    public void setDoctor_sex(String doctor_sex) {
        this.doctor_sex = doctor_sex;
    }
    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    public void setDoctor_number(String doctor_number) {
        this.doctor_number = doctor_number;
    }

    @Override
    public String toString() {
        return String.format("%-4s\t%-4s\t%-4s\t%-4s\t%-4s",doctor_number,doctor_name,doctor_sex,doctor_department,doctor_phone);
    }
}
