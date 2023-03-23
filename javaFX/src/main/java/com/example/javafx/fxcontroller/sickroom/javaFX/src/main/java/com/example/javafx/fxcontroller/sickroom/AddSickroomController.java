package com.example.javafx.fxcontroller.sickroom;

import com.example.javafx.source.sickroom.proj.SickRoom;
import com.example.javafx.source.sickroom.service.SickRoomService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddSickroomController {
    @FXML public TextField txtWardNo;
    @FXML public TextField txtWardType;
    @FXML public TextField txtDepartment;
    @FXML public TextField txtNumberofbeds;
    @FXML public TextField txtRemaingbeds;
    @FXML public TextField txtWardDoctor;
    private final SickRoomService sickRoomService=new SickRoomService();

    public void okClick(MouseEvent mouseEvent) throws SQLException {
        SickRoom sickRoom=new SickRoom();
        sickRoom.setWardNo(txtWardNo.getText());
        sickRoom.setWardType(txtWardType.getText());
        sickRoom.setDepartment(txtDepartment.getText());
        sickRoom.setNumberofbeds(Integer.parseInt(txtNumberofbeds.getText()));
        sickRoom.setRemainingbeds(Integer.parseInt(txtRemaingbeds.getText()));
        sickRoom.setWardDoctor(txtWardDoctor.getText());
        sickRoomService.addSickRoom(sickRoom);
        Stage stage=(Stage) txtWardNo.getScene().getWindow();
        stage.close();
    }
}
