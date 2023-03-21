package com.example.javafx.source.nurse.service;

import com.example.javafx.DBuntils.DataBaseService.DB_NurseService;
import com.example.javafx.source.nurse.proj.Nurse;

import java.sql.SQLException;
import java.util.ArrayList;

public class NurseService {
    private ArrayList<Nurse> nurses =new ArrayList<>();

    public ArrayList<Nurse> getNurses(int first,int end){
        int size=nurses.size();
        if(size>end-1)size=end-1;
        //提取20个数据显示
        if(first<=size)return new ArrayList<>(nurses.subList(first,size));
        else return null;
    }
    //分页查询信息
    public ArrayList<Nurse> getNurses(int page) throws SQLException {
        nurses= DB_NurseService.getData(page);
        return nurses;
    }
    //按护士编号或负责病房编号模糊或精确查询信息
    public ArrayList<Nurse> fin_Nurse(String key,String value) throws SQLException{
        nurses=DB_NurseService.getData(key,value);
        int size=nurses.size();
        if(size>19)size=19;
        //提取前20个数据显示
        return new ArrayList<>(nurses.subList(0,size));
    }
    //查找指定护士编号的信息
    public Nurse findOnenurse_number(String nurse_number) throws SQLException{
        for (Nurse nurse:nurses) {
            if (nurse_number.equals(nurse.getNurse_number())){
                return nurse;
            }
        }
        return null;
    }
    //修改指定护士的信息
    public ArrayList<Nurse> updateNurse(String nurse_number, Nurse nurse) throws SQLException{
        int index= nurses.indexOf(findOnenurse_number(nurse_number));
        nurses.set(index, nurse);
        DB_NurseService.updateData(nurse);
        return nurses;
    }
    //添加护士信息
    public void addNurse(Nurse nurse) throws SQLException{
        nurses.add(nurse);
        DB_NurseService.addData(nurse);
    }
    //删除护士信息
    public ArrayList<Nurse> deleteNurse(String nurse_number) throws SQLException{
        nurses.remove(findOnenurse_number(nurse_number));
        DB_NurseService.deleteData(nurse_number);
        return nurses;
    }
}
