package com.example.javafx.fxcontroller.doctor;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.doctor.proj.Doctor;
import com.example.javafx.source.doctor.service.DoctorService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditDoctorController implements Initializable {
    @FXML public TextField txt_doctor_number;
    @FXML public TextField txt_doctor_name;
    @FXML public TextField txt_doctor_sex;
    @FXML public TextField txt_doctor_department;
    @FXML public TextField txt_doctor_phone;
    //获取doctorController
    DoctorController doctorController=(DoctorController) ControllerManager.controllerMap.get("doctorController");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Doctor doctor = doctorController.doctor;
        txt_doctor_number.setText(doctor.getDoctor_number());
        txt_doctor_name.setText(doctor.getDoctor_name());
        txt_doctor_sex.setText(doctor.getDoctor_sex());
        txt_doctor_department.setText(doctor.getDoctor_department());
        txt_doctor_phone.setText(doctor.getDoctor_phone());
    }

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        String doctor_number=txt_doctor_number.getText();
        String doctor_name=txt_doctor_name.getText();
        String doctor_sex=txt_doctor_sex.getText();
        String doctor_department= txt_doctor_department.getText();
        String doctor_phone=txt_doctor_phone.getText();
        Doctor doctor=new Doctor(doctor_number,doctor_name,doctor_sex,doctor_department,doctor_phone);
        DoctorService ds=new DoctorService();
        Stage stage=(Stage) txt_doctor_number.getScene().getWindow();
        stage.close();
        //更新表格数据
        doctorController.initTable(ds.updateDoctor(doctor_number,doctor));
    }
}
