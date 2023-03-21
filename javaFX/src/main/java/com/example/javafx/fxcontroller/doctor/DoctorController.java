package com.example.javafx.fxcontroller.doctor;

import com.example.javafx.fxcontroller.ControllerManager;
import com.example.javafx.source.doctor.proj.Doctor;
import com.example.javafx.source.doctor.service.DoctorService;
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

public class DoctorController implements Initializable {
    @FXML public ComboBox<String> comboboxFind;
    @FXML public TableView<Doctor> tableDoctor;
    @FXML public TableColumn<Object,Object> to_doctor_number;
    @FXML public TableColumn<Object,Object> to_doctor_name;
    @FXML public TableColumn<Object,Object> to_doctor_sex;
    @FXML public TableColumn<Object,Object> to_doctor_department;
    @FXML public TableColumn<Object, Object> to_doctor_phone;
    @FXML public MenuItem editClick;
    @FXML public TextField txtKey;
    @FXML public MenuItem deleteClick;
    @FXML public MenuItem addClick;
    private final DoctorService doctorService=new DoctorService();
    public Doctor doctor;
    private int page=1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //下拉框填充数据，sitem为ComboBox下拉列表组件的fx:id
        comboboxFind.getItems().add("分页查询");
        comboboxFind.getItems().add("医生编号");
        comboboxFind.getItems().add("所属科室");
        //选中下拉框中的第一项
        comboboxFind.getSelectionModel().select(0);
        //调用数据操作类获取数据加载数据表格（TableView）
        try {
            initTable(doctorService.getDoctors(page));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initTable(ArrayList<Doctor> doctors){
        //设置数据表格源
        ObservableList<Doctor> date= FXCollections.observableArrayList(doctors);
        //设置表格列与实际列对应
        to_doctor_number.setCellValueFactory(new PropertyValueFactory<>("doctor_number"));
        to_doctor_name.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        to_doctor_sex.setCellValueFactory(new PropertyValueFactory<>("doctor_sex"));
        to_doctor_department.setCellValueFactory(new PropertyValueFactory<>("doctor_department"));
        to_doctor_phone.setCellValueFactory(new PropertyValueFactory<>("doctor_phone"));
        tableDoctor.setItems(date);
    }
    //点击查找
    public void findClick(MouseEvent actionEvent) throws SQLException {
        if(txtKey.getText().isEmpty())return;
        //从数据库查询
        if(comboboxFind.getSelectionModel().getSelectedItem().equals("分页查询")) {
            page=Integer.parseInt(txtKey.getText());
            initTable(doctorService.getDoctors(page));
        }
        else {
            page=1;
            initTable(doctorService.fin_Doctor(comboboxFind.getSelectionModel().getSelectedItem(), txtKey.getText()));
        }
    }
    //编辑
    public void editButton(ActionEvent actionEvent) throws IOException {
        doctor= tableDoctor.getSelectionModel().getSelectedItem();
        ControllerManager.controllerMap.put("doctorController",this);
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/doctor/editDoctor-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("编辑医生信息");
        //设置窗体不关闭不能使用其他窗体
        stage.initModality(Modality.APPLICATION_MODAL);
        //设置窗体大小不变
        stage.setResizable(false);
        stage.show();
    }
    //删除
    public void deleteButton(ActionEvent actionEvent) throws SQLException{
        doctor= tableDoctor.getSelectionModel().getSelectedItem();
        initTable(doctorService.deleteDoctor(doctor.getDoctor_number()));
    }
    //添加
    public void addButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(
                "/com/example/javafx/fxml/doctor/addDoctor-view.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("添加医生信息");
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
           initTable(doctorService.getDoctors(page));
        }
        //从保留的查询结果查询
        else {
            //数据范围
            int first_num=(page-1)*20;
            int end_num=first_num+20;
            if(doctorService.getDoctors(first_num,end_num)==null)return;
            initTable(doctorService.getDoctors(first_num,end_num));
        }
    }
}
