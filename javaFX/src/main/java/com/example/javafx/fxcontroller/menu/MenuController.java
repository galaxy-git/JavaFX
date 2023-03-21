package com.example.javafx.fxcontroller.menu;

import com.example.javafx.fxcontroller.ControllerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML public ImageView sexist;
    @FXML public BorderPane menuPane;


    public void existsys(MouseEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("退出系统");
        alert.setHeaderText("您确定退出系统?");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            Stage stage=(Stage)sexist.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManager.controllerMap.put("menuController",this);
    }
}
