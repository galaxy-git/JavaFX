package com.example.javafx.fxcontroller.menu;

import com.example.javafx.fxcontroller.ControllerManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LeftMenuController {
    public void leftmenuClick01(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/sickroom/sickroom-view.fxml"));
        //Pane pane= fxmlLoader.load();
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        //menuController.menuPane.setCenter(pane);
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick02(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/doctor/doctor-view.fxml"));
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick03(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/nurse/nurse-view.fxml"));
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick04(MouseEvent mouseEvent) throws IOException{

    }
}
