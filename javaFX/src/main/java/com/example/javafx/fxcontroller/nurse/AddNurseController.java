package com.example.javafx.fxcontroller.nurse;

import com.example.javafx.source.nurse.proj.Nurse;
import com.example.javafx.source.nurse.service.NurseService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        nurse.setNurse_number(String.valueOf(txt_nurse_number));
        nurse.setNurse_name(String.valueOf(txt_nurse_name));
        nurse.setNurse_sex(String.valueOf(txt_nurse_sex));
        nurse.setNurse_titles(String.valueOf(txt_nurse_titles));
        nurse.setNurse_sickroom(String.valueOf(txt_nurse_sickroom));
        nurseService.addNurse(nurse);
    }
}
