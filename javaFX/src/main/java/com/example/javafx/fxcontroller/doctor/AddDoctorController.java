package com.example.javafx.fxcontroller.doctor;

import com.example.javafx.source.doctor.proj.Doctor;
import com.example.javafx.source.doctor.service.DoctorService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
        doctor.setDoctor_number(txt_doctor_number.getText());
        doctor.setDoctor_name(txt_doctor_name.getText());
        doctor.setDoctor_sex(txt_doctor_sex.getText());
        doctor.setDoctor_department(txt_doctor_department.getText());
        doctor.setDoctor_phone(txt_doctor_phone.getText());
        doctorService.addDoctor(doctor);
        Stage stage=(Stage) txt_doctor_number.getScene().getWindow();
        stage.close();
    }
}
