package com.example.javafx.DBuntils.DataBaseService;

import com.example.javafx.DBuntils.DBconnection;
import com.example.javafx.file.File_doctor;
import com.example.javafx.source.doctor.proj.Doctor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DB_DoctorService {
    //连接数据库
    static Connection connection;
    static File_doctor file_doctor=new File_doctor();
    static {
        try {
            connection = DBconnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //获取所有数据
    public static ArrayList<Doctor> getData() throws SQLException{
        ArrayList<Doctor> doctors=new ArrayList<>();
        Doctor dt;
        String sql="select 医生编号, 医生姓名, 性别, 所属科室,电话号码 from DB_doctor";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            dt = new Doctor(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            doctors.add(dt);
        }
        ps.close();
        return doctors;
    }
    //分页获取数据
    public static ArrayList<Doctor> getData(int page) throws SQLException{
        ArrayList<Doctor> doctors=new ArrayList<>();
        int pageSize=20;
        int first_num=(page-1)*pageSize+1;
        int end_num=first_num+pageSize-1;
        Doctor dt;
        String sql="select 医生编号, 医生姓名, 性别, 所属科室,电话号码 from DB_doctor having count(医生编号) between ? and ? order by 医生编号";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,first_num);
        ps.setInt(2,end_num);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            dt = new Doctor(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            doctors.add(dt);
        }
        ps.close();
        return doctors;
    }
    //按病房号或所属科室模糊或精确查询信息
    public static ArrayList<Doctor> getData(String key,String value) throws SQLException{
        ArrayList<Doctor> doctors=new ArrayList<>();
        Doctor dt;
        String sql="select 医生编号, 医生姓名, 性别, 所属科室,电话号码 from DB_doctor where ? like ? order by 医生编号";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,key);
        ps.setString(2,'%'+value+'%');
        ResultSet result= ps.executeQuery();
        while (result.next()){
            dt = new Doctor(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            doctors.add(dt);
        }
        ps.close();
        return doctors;
    }
    //更新数据库
    public static void updateData(Doctor doctor) throws SQLException{
        String sql="update DB_doctor set 医生编号=?,医生姓名=?,性别=?,所属科室=?,电话号码=? where 医生编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, doctor.getDoctor_number());
        ps.setString(2, doctor.getDoctor_name());
        ps.setString(3, doctor.getDoctor_sex());
        ps.setString(4, doctor.getDoctor_department());
        ps.setString(5,doctor.getDoctor_phone());
        ps.setString(6, doctor.getDoctor_number());
        ps.executeUpdate();
        ps.close();
    }
    public static void updateData(ArrayList<Doctor> doctors) throws SQLException{
        String sql="update DB_doctor set 医生编号=?,医生姓名=?,性别=?,所属科室=?,电话号码=? where 医生编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        for (Doctor doctor:doctors){
            ps.setString(1, doctor.getDoctor_number());
            ps.setString(2, doctor.getDoctor_name());
            ps.setString(3, doctor.getDoctor_sex());
            ps.setString(4, doctor.getDoctor_department());
            ps.setString(5, doctor.getDoctor_phone());
            ps.setString(6, doctor.getDoctor_number());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //为数据库增加数据
    public static void addData(Doctor doctor) throws SQLException{
        String sql= "insert into DB_doctor values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, doctor.getDoctor_number());
        ps.setString(2, doctor.getDoctor_name());
        ps.setString(3, doctor.getDoctor_sex());
        ps.setString(4, doctor.getDoctor_department());
        ps.setString(5, doctor.getDoctor_phone());
        ps.execute();
        ps.close();
    }
    public static void addData(ArrayList<Doctor> doctors) throws SQLException{
        String sql= "insert into DB_doctor values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        for(Doctor doctor:doctors){
            ps.setString(1, doctor.getDoctor_number());
            ps.setString(2, doctor.getDoctor_name());
            ps.setString(3, doctor.getDoctor_sex());
            ps.setString(4, doctor.getDoctor_department());
            ps.setString(5, doctor.getDoctor_phone());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //从数据库删除数据
    public static void deleteData(String Doctor_number) throws SQLException{
        String sql= "delete from DB_doctor where 医生编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, Doctor_number);
        ps.execute();
        ps.close();
    }
    //从文件读数据到数据库
    public static void getRead(){
        try {
            updateData(file_doctor.file_read());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //从数据库写数据到文件
    public static void getWrite(){
        try {
            file_doctor.file_write(getData());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
