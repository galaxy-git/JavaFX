package com.example.javafx.fxcontroller.nurse;

import com.example.javafx.source.nurse.proj.Nurse;
import com.example.javafx.source.nurse.service.NurseService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddNurseController {
    @FXML public TextField txt_nurse_number;
    @FXML public TextField txt_nurse_name;
    @FXML public TextField txt_nurse_sex;
    @FXML public TextField txt_nurse_titles;
    @FXML public TextField txt_nurse_sickroom;
    private final NurseService nurseService=new NurseService();

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        Nurse nurse=new Nurse();
        nurse.setNurse_number(txt_nurse_number.getText());
        nurse.setNurse_name(txt_nurse_name.getText());
        nurse.setNurse_sex(txt_nurse_sex.getText());
        nurse.setNurse_titles(txt_nurse_titles.getText());
        nurse.setNurse_sickroom(txt_nurse_sickroom.getText());
        nurseService.addNurse(nurse);
        Stage stage=(Stage) txt_nurse_number.getScene().getWindow();
        stage.close();
    }
}
