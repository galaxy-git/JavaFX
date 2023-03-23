package com.example.javafx;

import com.example.javafx.DBuntils.DataBaseService.DB_DoctorService;
import com.example.javafx.DBuntils.DataBaseService.DB_NurseService;
import com.example.javafx.DBuntils.DataBaseService.DB_SickRoomService;
import com.example.javafx.datacreate.Data_doctor;
import com.example.javafx.datacreate.Data_nurse;
import com.example.javafx.datacreate.Data_sickroom;
import com.example.javafx.file.File_doctor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainApplication.class.getResource("/com/example/javafx/fxml/menu/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //窗体最大化
        stage.setMaximized(true);
        //设置窗体无标题栏,关闭等按钮
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public static void main(String[] args) {
        File_doctor file_doctor=new File_doctor();
        if(file_doctor.getType()) {
            DB_DoctorService.getRead();
            DB_SickRoomService.getRead();
            DB_NurseService.getRead();
        } else {
            new Data_doctor();
            new Data_sickroom();
            new Data_nurse();
        }
        launch();
        DB_DoctorService.getWrite();
        DB_SickRoomService.getWrite();
        DB_NurseService.getWrite();
    }
}