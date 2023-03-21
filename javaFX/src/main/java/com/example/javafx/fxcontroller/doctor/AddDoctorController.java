package com.example.javafx.fxcontroller.doctor;

import com.example.javafx.source.doctor.proj.Doctor;
import com.example.javafx.source.doctor.service.DoctorService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class AddDoctorController {
    @FXML public TextField txt_doctor_number;
    @FXML public TextField txt_doctor_name;
    @FXML public TextField txt_doctor_sex;
    @FXML public TextField txt_doctor_department;
    @FXML public TextField txt_doctor_phone;
    private final DoctorService doctorService=new DoctorService();

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        Doctor doctor=new Doctor();
        doctor.setDoctor_number(String.valueOf(txt_doctor_number));
        doctor.setDoctor_name(String.valueOf(txt_doctor_name));
        doctor.setDoctor_sex(String.valueOf(txt_doctor_sex));
        doctor.setDoctor_department(String.valueOf(txt_doctor_department));
        doctor.setDoctor_phone(String.valueOf(txt_doctor_phone));
        doctorService.addDoctor(doctor);
    }
}
