package com.example.javafx.fxcontroller.sickroom;

import com.example.javafx.source.sickroom.proj.SickRoom;
import com.example.javafx.source.sickroom.service.SickRoomService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        sickRoom.setWardNo(String.valueOf(txtWardNo));
        sickRoom.setWardType(String.valueOf(txtWardType));
        sickRoom.setDepartment(String.valueOf(txtDepartment));
        sickRoom.setNumberofbeds(Integer.parseInt(String.valueOf(txtNumberofbeds)));
        sickRoom.setRemainingbeds(Integer.parseInt(String.valueOf(txtRemaingbeds)));
        sickRoom.setWardDoctor(String.valueOf(txtWardDoctor));
        sickRoomService.addSickRoom(sickRoom);
    }
}
