package com.example.javafx.fxcontroller.sickroom;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.sickroom.proj.SickRoom;
import com.example.javafx.source.sickroom.service.SickRoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SickroomController implements Initializable {
    @FXML public ComboBox<String> comboboxFind;
    @FXML public TableView<SickRoom> tableSickroom;
    @FXML public TableColumn<Object, Object> towardNo;
    @FXML public TableColumn<Object, Object> towardType;
    @FXML public TableColumn<Object, Object> toDepartment;
    @FXML public TableColumn<Object, Object> toNumberofbeds;
    @FXML public TableColumn<Object, Object> toRemainingbeds;
    @FXML public TableColumn<Object, Object> towardDoctor;
    @FXML public TextField txtKey;
    @FXML public MenuItem editClick;
    @FXML public MenuItem deleteClick;
    @FXML public MenuItem addClick;
    private final SickRoomService sickRoomService=new SickRoomService();
    public SickRoom sickRoom;
    private int page=1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //下拉框填充数据，sitem为ComboBox下拉列表组件的fx:id
        comboboxFind.getItems().add("分页查询");
        comboboxFind.getItems().add("病房号");
        comboboxFind.getItems().add("所属科室");
        //选中下拉框中的第一项
        comboboxFind.getSelectionModel().select(0);
        //调用数据操作类获取数据加载数据表格（TableView）
        try {
            initTable(sickRoomService.getSickRooms(page));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initTable(ArrayList<SickRoom> sickrooms){
        //设置数据表格源
        ObservableList<SickRoom> date= FXCollections.observableArrayList(sickrooms);
        //设置表格列与实际列对应
        towardNo.setCellValueFactory(new PropertyValueFactory<>("wardNo"));
        towardType.setCellValueFactory(new PropertyValueFactory<>("wardType"));
        toDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        toNumberofbeds.setCellValueFactory(new PropertyValueFactory<>("numberofbeds"));
        toRemainingbeds.setCellValueFactory(new PropertyValueFactory<>("remainingbeds"));
        towardDoctor.setCellValueFactory(new PropertyValueFactory<>("wardDoctor"));
        tableSickroom.setItems(date);
    }
    //点击查找
    public void findClick(MouseEvent actionEvent) throws SQLException {
        if(txtKey.getText().isEmpty())return;
        //从数据库查询
        if(comboboxFind.getId().equals("分页查询")) {
            page=Integer.parseInt(txtKey.getText());
            initTable(sickRoomService.getSickRooms(page));
        }
        else {
            page=1;
            initTable(sickRoomService.fin_SickRoom(comboboxFind.getId(), txtKey.getText()));
        }
    }
    //编辑
    public void editButton(ActionEvent actionEvent) throws IOException {
        sickRoom= tableSickroom.getSelectionModel().getSelectedItem();
        ControllerManager.controllerMap.put("sickroomController",this);
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/sickroom/editSickroom-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("编辑病房信息");
        //设置窗体不关闭不能使用其他窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗体大小不变
        stage.setResizable(false);
        stage.show();
    }
    //删除
    public void deleteButton(ActionEvent actionEvent) throws SQLException{
        sickRoom= tableSickroom.getSelectionModel().getSelectedItem();
        initTable(sickRoomService.deleteSickRoom(sickRoom.getWardNo()));
    }
    //添加
    public void addButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/sickroom/addSickroom-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("添加病房信息");
        //设置窗体不关闭不能使用其他窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗体大小不变
        stage.setResizable(false);
        stage.show();
    }
    //下一页
    public void nextClick(MouseEvent mouseEvent) throws SQLException{
        if(page==0)return;
        page=page+1;
        //从数据库查询
        if(comboboxFind.getId().equals("分页查询")) {
            initTable(sickRoomService.getSickRooms(page));
        }
        //从保留的查询结果查询
        else {
            //数据范围
            int first_num=(page-1)*20;
            int end_num=first_num+20;
            if(sickRoomService.getSickRooms(first_num,end_num)==null)return;
            initTable(sickRoomService.getSickRooms(first_num,end_num));
        }
    }
}
