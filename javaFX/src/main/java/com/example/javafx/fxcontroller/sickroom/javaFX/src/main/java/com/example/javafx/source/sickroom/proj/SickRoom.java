package com.example.javafx.source.sickroom.proj;

import java.io.Serializable;

public class SickRoom implements Serializable {
    //病房号
    private String wardNo;
    //病房类型
    private String wardType;
    //所属科室
    private String department;
    //床位数
    private int numberofbeds;
    //剩余床位数
    private int remainingbeds;
    //负责医生
    private String wardDoctor;

    public SickRoom(){}
    public SickRoom(String wardNo, String wardType, String department, int numberofbeds, int remainingbeds, String wardDoctor){
        this.wardNo = wardNo;
        this.department = department;
        this.wardType = wardType;
        this.numberofbeds = numberofbeds;
        this.remainingbeds = remainingbeds;
        this.wardDoctor = wardDoctor;
    }
    public void setWardNo(String wardNo){
        this.wardNo = wardNo;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public void setWardType(String wardType){
        this.wardType = wardType;
    }
    public void setNumberofbeds(int numberofbeds){
        this.numberofbeds = numberofbeds;
    }
    public void setRemainingbeds(int remainingbeds) {
        this.remainingbeds = remainingbeds;
    }
    public void setWardDoctor(String wardDoctor) {
        this.wardDoctor = wardDoctor;
    }
    public String getWardDoctor() {
        return wardDoctor;
    }
    public int getRemainingbeds() {
        return remainingbeds;
    }
    public String getWardNo( ){
        return wardNo;
    }
    public String getDepartment(){
        return department;
    }
    public String getWardType(){
        return wardType;
    }
    public int getNumberofbeds(){
        return numberofbeds;
    }

    @Override
    public String toString() {
        return String.format("%-4s\t%-4s\t%-4s\t%-4d\t%-4d\t%-4s", wardNo,department,wardType,numberofbeds,remainingbeds,wardDoctor);
    }
}
