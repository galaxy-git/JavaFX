package com.example.javafx.DBuntils.DataBaseService;

import com.example.javafx.DBuntils.DBconnection;
import com.example.javafx.file.File_sickroom;
import com.example.javafx.source.sickroom.proj.SickRoom;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//*除非使用=或不使用条件查询,否则不能用?占位符
//数据库关闭
public class DB_SickRoomService {
    //连接数据库
    static Connection connection;
    static File_sickroom file_sickroom=new File_sickroom();
    static {
        try {
            connection = DBconnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取所有数据
    public static ArrayList<SickRoom> getData() throws SQLException{
        ArrayList<SickRoom> sickRooms=new ArrayList<>();
        SickRoom sR;
        String sql="select 病房号,病房类型,所属科室,床位数,剩余床位数,负责医生 from DB_sickroom";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            sR = new SickRoom(result.getString(1), result.getString(2),
                    result.getString(3), result.getInt(4), result.getInt(5), result.getString(6));
            sickRooms.add(sR);
        }
        ps.close();
        return sickRooms;
    }
    //分页获取数据
    public static ArrayList<SickRoom> getData(int page) throws SQLException{
        ArrayList<SickRoom> sickRooms=new ArrayList<>();
        int pageSize=20;
        int first_num=(page-1)*pageSize+1;
        int end_num=first_num+pageSize-1;
        SickRoom sR;
        //top 后不能接占位符
        String sql="select top "+pageSize+" * from (select top "+end_num+" * from DB_sickroom order by 病房号) s order by s.病房号 desc";
        PreparedStatement ps=connection.prepareStatement(sql);
//        ps.setInt(1,first_num);
//        ps.setInt(2,end_num);
        ResultSet result= ps.executeQuery();
        while (result.next()){
            sR = new SickRoom(result.getString(1), result.getString(2),
                    result.getString(3), result.getInt(4), result.getInt(5), result.getString(6));
            sickRooms.add(sR);
        }
        ps.close();
        return sickRooms;
    }
    //按病房号或所属科室模糊或精确查询信息
    public static ArrayList<SickRoom> getData(String key,String value) throws SQLException{
        ArrayList<SickRoom> sickRooms=new ArrayList<>();
        SickRoom sR;
        //like不能用占位符
        String sql="select 病房号,病房类型,所属科室,床位数,剩余床位数,负责医生 from DB_sickroom where "+key+" like '%"+value+"%' order by 病房号";
        PreparedStatement ps=connection.prepareStatement(sql);
//        ps.setString(1,key);
//        ps.setString(2,"'%"+value+"%'");
        ResultSet result= ps.executeQuery();
        while (result.next()){
            sR = new SickRoom(result.getString(1), result.getString(2),
                    result.getString(3), result.getInt(4), result.getInt(5), result.getString(6));
            sickRooms.add(sR);
        }
        ps.close();
        return sickRooms;
    }
    //更新数据库
    public static void updateData(SickRoom sickRoom) throws SQLException{
        String sql="update DB_sickroom set 病房号=?,病房类型=?,所属科室=?,床位数=?,剩余床位数=?,负责医生=? where 病房号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,sickRoom.getWardNo());
        ps.setString(2,sickRoom.getWardType());
        ps.setString(3,sickRoom.getDepartment());
        ps.setString(4, String.valueOf(sickRoom.getNumberofbeds()));
        ps.setString(5, String.valueOf(sickRoom.getRemainingbeds()));
        ps.setString(6,sickRoom.getWardDoctor());
        ps.setString(7, sickRoom.getWardNo());
        ps.executeUpdate();
        ps.close();
    }
    public static void updateData(ArrayList<SickRoom> sickRooms) throws SQLException{
        String sql="update DB_sickroom set 病房号=?,病房类型=?,所属科室=?,床位数=?,剩余床位数=?,负责医生=? where 病房号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        for (SickRoom sickRoom:sickRooms){
            ps.setString(1, sickRoom.getWardNo());
            ps.setString(2, sickRoom.getWardType());
            ps.setString(3, sickRoom.getDepartment());
            ps.setString(4, String.valueOf(sickRoom.getNumberofbeds()));
            ps.setString(5, String.valueOf(sickRoom.getRemainingbeds()));
            ps.setString(6, sickRoom.getWardDoctor());
            ps.setString(7, sickRoom.getWardNo());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //为数据库增加数据
    public static void addData(SickRoom sickRoom) throws SQLException{
        String sql= "insert into DB_sickroom values(?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, sickRoom.getWardNo());
        ps.setString(2, sickRoom.getWardType());
        ps.setString(3,sickRoom.getDepartment());
        ps.setString(4,String.valueOf(sickRoom.getNumberofbeds()));
        ps.setString(5, String.valueOf(sickRoom.getRemainingbeds()));
        ps.setString(6,sickRoom.getWardDoctor());
        ps.execute();
        ps.close();
    }
    public static void addData(ArrayList<SickRoom> sickRooms) throws SQLException{
        String sql= "insert into DB_sickroom values(?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(sql);
        for(SickRoom sickRoom : sickRooms){
            ps.setString(1, sickRoom.getWardNo());
            ps.setString(2, sickRoom.getWardType());
            ps.setString(3, sickRoom.getDepartment());
            ps.setString(4, String.valueOf(sickRoom.getNumberofbeds()));
            ps.setString(5, String.valueOf(sickRoom.getRemainingbeds()));
            ps.setString(6, sickRoom.getWardDoctor());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
    //从数据库删除数据
    public static void deleteData(String wardNo) throws SQLException{
        String sql= "delete from DB_sickroom where 病房号=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1, wardNo);
        ps.execute();
        ps.close();
    }
    //从文件读数据到数据库
    public static void getRead(){
        try {
            updateData(file_sickroom.file_read());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //从数据库写数据到文件
    public static void getWrite(){
        try {
            file_sickroom.file_write(getData());
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
