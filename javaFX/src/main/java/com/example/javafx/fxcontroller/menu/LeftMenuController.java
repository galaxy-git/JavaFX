package com.example.javafx.fxcontroller.menu;

import com.example.javafx.fxcontroller.ControllerManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LeftMenuController {
    public void leftmenuClick01(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/hello-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load(),320,240);
        stage.setScene(scene);
        //设置窗体不关闭不能使用其他窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void leftmenuClick02(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/sickroom/sickroom-view.fxml"));
        //Pane pane= fxmlLoader.load();
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        //menuController.menuPane.setCenter(pane);
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick03(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/doctor/doctor-view.fxml"));
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick04(MouseEvent mouseEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/javafx/fxml/nurse/nurse-view.fxml"));
        MenuController menuController=(MenuController) ControllerManager.controllerMap.get("menuController");
        menuController.menuPane.setCenter(fxmlLoader.load());
    }

    public void leftmenuClick05(MouseEvent mouseEvent) {
    }
}
