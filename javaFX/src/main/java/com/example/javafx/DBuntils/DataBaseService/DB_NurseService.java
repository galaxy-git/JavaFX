package com.example.javafx.DBuntils.DataBaseService;

import com.example.javafx.DBuntils.DBconnection;
import com.example.javafx.file.File_nurse;
import com.example.javafx.source.nurse.proj.Nurse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//*除非使用=或不使用条件查询,否则不能用?占位符
public class DB_NurseService {
    //连接数据库
    static Connection connection;
    static File_nurse file_nurse=new File_nurse();
    static {
        try {
            connection = DBconnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取所有数据
    public static ArrayList<Nurse> getData() throws SQLException{
        ArrayList<Nurse> nurses=new ArrayList<>();
        Nurse ns;
        String sql="select 护士编号,护士姓名,性别,职称,负责病房编号 from DB_nurse";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            ns = new Nurse(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            nurses.add(ns);
        }
        ps.close();
        return nurses;
    }
    //分页获取数据
    public static ArrayList<Nurse> getData(int page) throws SQLException{
        ArrayList<Nurse> nurses=new ArrayList<>();
        int pageSize=20;
        int first_num=(page-1)*pageSize+1;
        int end_num=first_num+pageSize-1;
        Nurse ns;
        //top 后不能接占位符
        String sql="select top "+pageSize+" * from (select top "+end_num+" * from DB_nurse order by 护士编号) n order by n.护士编号 desc";
        PreparedStatement ps=connection.prepareStatement(sql);
//        ps.setInt(1,first_num);
//        ps.setInt(2,end_num);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            ns = new Nurse(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            nurses.add(ns);
        }
        ps.close();
        return nurses;
    }
    //按病房号或所属科室模糊或精确查询信息
    public static ArrayList<Nurse> getData(String key,String value) throws SQLException{
        ArrayList<Nurse> nurses=new ArrayList<>();
        Nurse ns;
        //like不能用占位符
        String sql="select 护士编号,护士姓名,性别,职称,负责病房编号 from DB_nurse where "+key+" like '%"+value+"%' order by 护士编号";
        PreparedStatement ps=connection.prepareStatement(sql);
//        ps.setString(1,key);
//        ps.setString(2,"'%"+value+"%'");
        ResultSet result= ps.executeQuery();
        while (result.next()){
            ns = new Nurse(result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getString(5));
            nurses.add(ns);
        }
        ps.close();
        return nurses;
    }
    //更新数据库
    public static void updateData(Nurse nurse) throws SQLException{
        String sql="update DB_nurse set 护士编号=?,护士姓名=?,性别=?,职称=?,负责病房编号=? where 护士编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,nurse.getNurse_number());
        ps.setString(2,nurse.getNurse_name());
        ps.setString(3,nurse.getNurse_sex());
        ps.setString(4, nurse.getNurse_titles());
        ps.setString(5, nurse.getNurse_sickroom());
        ps.setString(6,nurse.getNurse_number());
        ps.executeUpdate();
        ps.close();
    }
    public static void updateData(ArrayList<Nurse> nurses) throws SQLException{
        String sql="update DB_nurse set 护士编号=?,护士姓名=?,性别=?,职称=?,负责病房编号=? where 护士编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        for (Nurse nurse:nurses){
            ps.setString(1, nurse.getNurse_number());
            ps.setString(2, nurse.getNurse_name());
            ps.setString(3, nurse.getNurse_sex());
            ps.setString(4, nurse.getNurse_titles());
            ps.setString(5, nurse.getNurse_sickroom());
            ps.setString(6, nurse.getNurse_number());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //为数据库增加数据
    public static void addData(Nurse nurse) throws SQLException{
        String sql= "insert into DB_nurse values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,nurse.getNurse_number());
        ps.setString(2,nurse.getNurse_name());
        ps.setString(3,nurse.getNurse_sex());
        ps.setString(4, nurse.getNurse_titles());
        ps.setString(5, nurse.getNurse_sickroom());
        ps.execute();
        ps.close();
    }
    public static void addData(ArrayList<Nurse> nurses) throws SQLException{
        String sql= "insert into DB_nurse values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        for (Nurse nurse : nurses){
            ps.setString(1, nurse.getNurse_number());
            ps.setString(2, nurse.getNurse_name());
            ps.setString(3, nurse.getNurse_sex());
            ps.setString(4, nurse.getNurse_titles());
            ps.setString(5, nurse.getNurse_sickroom());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //从数据库删除数据
    public static void deleteData(String Nurse_number) throws SQLException{
        String sql= "delete from DB_nurse where 护士编号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, Nurse_number);
        ps.execute();
        ps.close();
    }
    //从文件读数据到数据库
    public static void getRead(){
        try {
            updateData(file_nurse.file_read());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //从数据库写数据到文件
    public static void getWrite(){
        try {
            file_nurse.file_write(getData());
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
