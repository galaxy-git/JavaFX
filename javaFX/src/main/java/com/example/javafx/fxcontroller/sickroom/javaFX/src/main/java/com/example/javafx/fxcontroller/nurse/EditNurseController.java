package com.example.javafx.fxcontroller.nurse;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.nurse.proj.Nurse;
import com.example.javafx.source.nurse.service.NurseService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditNurseController implements Initializable {
    @FXML public TextField txt_nurse_number;
    @FXML public TextField txt_nurse_name;
    @FXML public TextField txt_nurse_sex;
    @FXML public TextField txt_nurse_titles;
    @FXML public TextField txt_nurse_sickroom;
    //获取nurseController
    NurseController nurseController=(NurseController) ControllerManager.controllerMap.get("nurseController");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Nurse nurse= nurseController.nurse;
        txt_nurse_number.setText(nurse.getNurse_number());
        txt_nurse_name.setText(nurse.getNurse_name());
        txt_nurse_sex.setText(nurse.getNurse_sex());
        txt_nurse_titles.setText(nurse.getNurse_titles());
        txt_nurse_sickroom.setText(nurse.getNurse_sickroom());
    }

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        String nurse_number=txt_nurse_number.getText();
        String nurse_name=txt_nurse_name.getText();
        String nurse_sex=txt_nurse_sex.getText();
        String nurse_titles= txt_nurse_titles.getText();
        String nurse_sickroom= txt_nurse_sickroom.getText();
        Nurse nurse=new Nurse(nurse_number,nurse_name,nurse_sex,nurse_titles,nurse_sickroom);
        NurseService ns=new NurseService();
        Stage stage=(Stage) txt_nurse_number.getScene().getWindow();
        stage.close();
        //更新表格数据
        nurseController.initTable(ns.updateNurse(nurse_number,nurse));
    }
}
