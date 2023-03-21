package com.example.javafx.source.sickroom.service;

import com.example.javafx.DBuntils.DataBaseService.DB_SickRoomService;
import com.example.javafx.source.sickroom.proj.SickRoom;

import java.sql.SQLException;
import java.util.ArrayList;

public class SickRoomService {
    private ArrayList<SickRoom> sickRooms =new ArrayList<>();

    public ArrayList<SickRoom> getSickRooms(int first,int end){
        int size=sickRooms.size();
        if(size>end-1)size=end-1;
        //提取20个数据显示
        if(first<=size)return new ArrayList<> (sickRooms.subList(first,size));
        else return null;
    }
    //分页查询信息
    public ArrayList<SickRoom> getSickRooms(int page) throws SQLException {
        sickRooms=DB_SickRoomService.getData(page);
        return sickRooms;
    }
    //按病房号或所属科室模糊或精确查询信息
    public ArrayList<SickRoom> fin_SickRoom(String key,String value) throws SQLException{
        sickRooms=DB_SickRoomService.getData(key,value);
        int size=sickRooms.size();
        if(size>19)size=19;
        //提取前20个数据显示
        return new ArrayList<> (sickRooms.subList(0,size));
    }
    //查找指定病房号的信息
    public SickRoom findOnewardNo(String wardNo) throws SQLException{
        for (SickRoom sickRoom : sickRooms) {
            if (wardNo.equals(sickRoom.getWardNo())){
                return sickRoom;
            }
        }
        return null;
    }
    //修改指定病房的信息
    public ArrayList<SickRoom> updateSickRoom(String wardNo, SickRoom sickRoom) throws SQLException{
        int index= sickRooms.indexOf(findOnewardNo(wardNo));
        sickRooms.set(index, sickRoom);
        DB_SickRoomService.updateData(sickRoom);
        return sickRooms;
    }
    //添加病房信息
    public void addSickRoom(SickRoom sickRoom) throws SQLException{
        sickRooms.add(sickRoom);
        DB_SickRoomService.addData(sickRoom);
    }
    //删除病房信息
    public ArrayList<SickRoom> deleteSickRoom(String wardNo) throws SQLException{
        sickRooms.remove(findOnewardNo(wardNo));
        DB_SickRoomService.deleteData(wardNo);
        return sickRooms;
    }
}
