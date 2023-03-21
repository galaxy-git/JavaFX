package com.example.javafx.fxcontroller.nurse;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.nurse.proj.Nurse;
import com.example.javafx.source.nurse.service.NurseService;
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

public class NurseController implements Initializable {
    @FXML public ComboBox<String> comboboxFind;
    @FXML public TableView<Nurse> tableNurse;
    @FXML public TableColumn<Object,Object> to_nurse_number;
    @FXML public TableColumn<Object,Object> to_nurse_name;
    @FXML public TableColumn<Object,Object> to_nurse_sex;
    @FXML public TableColumn<Object,Object> to_nurse_titles;
    @FXML public TableColumn<Object,Object> to_nurse_sickroom;
    @FXML public MenuItem editClick;
    @FXML public TextField txtKey;
    @FXML public MenuItem deleteClick;
    @FXML public MenuItem addClick;
    private final NurseService nurseService=new NurseService();
    public Nurse nurse;
    private int page=1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //下拉框填充数据，sitem为ComboBox下拉列表组件的fx:id
        comboboxFind.getItems().add("分页查询");
        comboboxFind.getItems().add("护士编号");
        comboboxFind.getItems().add("负责病房编号");
        //选中下拉框中的第一项
        comboboxFind.getSelectionModel().select(0);
        //调用数据操作类获取数据加载数据表格（TableView）
        try {
            initTable(nurseService.getNurses(page));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initTable(ArrayList<Nurse> nurses){
        //设置数据表格源
        ObservableList<Nurse> date= FXCollections.observableArrayList(nurses);
        //设置表格列与实际列对应
        to_nurse_number.setCellValueFactory(new PropertyValueFactory<>("nurse_number"));
        to_nurse_name.setCellValueFactory(new PropertyValueFactory<>("nurse_name"));
        to_nurse_sex.setCellValueFactory(new PropertyValueFactory<>("nurse_sex"));
        to_nurse_titles.setCellValueFactory(new PropertyValueFactory<>("nurse_titles"));
        to_nurse_sickroom.setCellValueFactory(new PropertyValueFactory<>("nurse_sickroom"));
        tableNurse.setItems(date);
    }
    //点击查找
    public void findClick(MouseEvent actionEvent) throws SQLException {
        if(txtKey.getText().isEmpty())return;
        //从数据库查询
        if(comboboxFind.getSelectionModel().getSelectedItem().equals("分页查询")) {
            page=Integer.parseInt(txtKey.getText());
            initTable(nurseService.getNurses(page));
        }
        else {
            page=1;
            initTable(nurseService.fin_Nurse(comboboxFind.getSelectionModel().getSelectedItem(), txtKey.getText()));
        }
    }
    //编辑
    public void editButton(ActionEvent actionEvent) throws IOException {
        nurse= tableNurse.getSelectionModel().getSelectedItem();
        ControllerManager.controllerMap.put("nurseController",this);
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/nurse/editNurse-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("编辑护士信息");
        //设置窗体不关闭不能使用其他窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗体大小不变
        stage.setResizable(false);
        stage.show();
    }
    //删除
    public void deleteButton(ActionEvent actionEvent) throws SQLException{
        nurse= tableNurse.getSelectionModel().getSelectedItem();
        initTable(nurseService.deleteNurse(nurse.getNurse_number()));
    }
    //添加
    public void addButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/nurse/addNurse-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("添加护士信息");
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
        //comboboxFind.getSelectionModel().getSelectedItem()获取下拉框第一项
        if(comboboxFind.getSelectionModel().getSelectedItem().equals("分页查询")) {
            initTable(nurseService.getNurses(page));
        }
        //从保留的查询结果查询
        else {
            //数据范围
            int first_num=(page-1)*20;
            int end_num=first_num+20;
            if(nurseService.getNurses(first_num,end_num)==null)return;
            initTable(nurseService.getNurses(first_num,end_num));
        }
    }
}
