package com.example.javafx.source.nurse.proj;

import java.io.Serializable;

public class Nurse implements Serializable {
    //护士编号
    private String nurse_number;
    //护士姓名
    private String nurse_name;
    //性别
    private String nurse_sex;
    //职称
    private String nurse_titles;
    //负责病房编号
    private String nurse_sickroom;

    public Nurse() {}
    public Nurse(String nurse_number,String nurse_name,String nurse_sex,String nurse_titles,String nurse_sickroom){
        this.nurse_number=nurse_number;
        this.nurse_name=nurse_name;
        this.nurse_sex=nurse_sex;
        this.nurse_titles=nurse_titles;
        this.nurse_sickroom=nurse_sickroom;
    }

    public String getNurse_number() {
        return nurse_number;
    }
    public String getNurse_name() {
        return nurse_name;
    }
    public String getNurse_sex() {
        return nurse_sex;
    }
    public String getNurse_titles() {
        return nurse_titles;
    }
    public String getNurse_sickroom() {
        return nurse_sickroom;
    }
    public void setNurse_sickroom(String nurse_sickroom) {
        this.nurse_sickroom = nurse_sickroom;
    }
    public void setNurse_titles(String nurse_titles) {
        this.nurse_titles = nurse_titles;
    }
    public void setNurse_sex(String nurse_sex) {
        this.nurse_sex = nurse_sex;
    }
    public void setNurse_name(String nurse_name) {
        this.nurse_name = nurse_name;
    }
    public void setNurse_number(String nurse_number) {
        this.nurse_number = nurse_number;
    }

    @Override
    public String toString() {
        return String.format("%-4s\t%-4s\t%-4s\t%-4s\t%-4s",nurse_number,nurse_name,nurse_sex,nurse_titles,nurse_sickroom);
    }
}
