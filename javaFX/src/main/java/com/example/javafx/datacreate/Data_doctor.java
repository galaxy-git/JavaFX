package com.example.javafx.datacreate;

import com.example.javafx.DBuntils.DataBaseService.DB_DoctorService;
import com.example.javafx.source.doctor.proj.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class Data_doctor {
    private static final RandomDataUntils random=new RandomDataUntils();
    private static final ArrayList<Doctor> doctors=new ArrayList<>();
    static {
        for(int i=0;i<50;i++){
            doctors.add(new Doctor(random.getRandomNumber("doctor"),random.getRandomName(),random.name_sex,random.getDepartment(),random.getRandomPhone()));
        }
        try {
            DB_DoctorService.addData(doctors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Doctor> getDoctors(){
        return doctors;
    }
}
