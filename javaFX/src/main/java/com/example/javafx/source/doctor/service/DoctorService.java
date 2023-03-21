package com.example.javafx.source.doctor.service;

import com.example.javafx.DBuntils.DataBaseService.DB_DoctorService;
import com.example.javafx.source.doctor.proj.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorService {
    private static ArrayList<Doctor> doctors =new ArrayList<>();

    public ArrayList<Doctor> getDoctors(int first,int end){
        int size=doctors.size();
        if(size>end-1)size=end-1;
        //提取20个数据显示
        if(first<=size)return new ArrayList<>(doctors.subList(first,size));
        else return null;
    }
    //分页查询信息
    public ArrayList<Doctor> getDoctors(int page) throws SQLException {
        doctors= DB_DoctorService.getData(page);
        return doctors;
    }
    //按医生编号或所属科室模糊或精确查询信息
    public ArrayList<Doctor> fin_Doctor(String key,String value) throws SQLException{
        doctors= DB_DoctorService.getData(key,value);
        int size=doctors.size();
        if(size>19)size=19;
        //提取前20个数据显示
        return new ArrayList<>(doctors.subList(0,size));
    }
    //查找指定医生编号的信息
    public Doctor findOnedoctor_number(String doctor_number) {
        for (Doctor doctor:doctors) {
            if (doctor_number.equals(doctor.getDoctor_number())){
                return doctor;
            }
        }
        return null;
    }
    //修改指定医生的信息
    public ArrayList<Doctor> updateDoctor(String doctor_number, Doctor doctor) throws SQLException{
        int index= doctors.indexOf(findOnedoctor_number(doctor_number));
        doctors.set(index, doctor);
        DB_DoctorService.updateData(doctor);
        return doctors;
    }
    //添加医生信息
    public void addDoctor(Doctor doctor) throws SQLException{
        doctors.add(doctor);
        DB_DoctorService.addData(doctor);
    }
    //删除医生信息
    public ArrayList<Doctor> deleteDoctor(String doctor_number) throws SQLException{
        doctors.remove(findOnedoctor_number(doctor_number));
        DB_DoctorService.deleteData(doctor_number);
        return doctors;
    }
}
