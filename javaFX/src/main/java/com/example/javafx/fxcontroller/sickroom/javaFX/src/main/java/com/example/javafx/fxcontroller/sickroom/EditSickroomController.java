package com.example.javafx.fxcontroller.sickroom;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.sickroom.proj.SickRoom;
import com.example.javafx.source.sickroom.service.SickRoomService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditSickroomController implements Initializable {
    @FXML public TextField txtWardNo;
    @FXML public TextField txtWardType;
    @FXML public TextField txtDepartment;
    @FXML public TextField txtNumberofbeds;
    @FXML public TextField txtRemaingbeds;
    @FXML public TextField txtWardDoctor;
    //获取sickroomController
    SickroomController sickroomController=(SickroomController) ControllerManager.controllerMap.get("sickroomController");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SickRoom sickRoom= sickroomController.sickRoom;
        txtWardNo.setText(sickRoom.getWardNo());
        txtWardType.setText(sickRoom.getWardType());
        txtDepartment.setText(sickRoom.getDepartment());
        txtNumberofbeds.setText(String.valueOf(sickRoom.getNumberofbeds()));
        txtRemaingbeds.setText(String.valueOf(sickRoom.getRemainingbeds()));
        txtWardDoctor.setText(sickRoom.getWardDoctor());
    }

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        String wardNo=txtWardNo.getText();
        String wardType=txtWardType.getText();
        String department=txtDepartment.getText();
        int numberofbeds= Integer.parseInt(txtNumberofbeds.getText());
        int remaingbeds= Integer.parseInt(txtRemaingbeds.getText());
        String wardDoctor=txtWardDoctor.getText();
        SickRoom sickRoom=new SickRoom(wardNo,wardType,department,numberofbeds,remaingbeds, wardDoctor);
        SickRoomService sc=new SickRoomService();
        Stage stage=(Stage) txtWardNo.getScene().getWindow();
        stage.close();
        //更新表格数据
        sickroomController.initTable(sc.updateSickRoom(wardNo,sickRoom));
    }
}
