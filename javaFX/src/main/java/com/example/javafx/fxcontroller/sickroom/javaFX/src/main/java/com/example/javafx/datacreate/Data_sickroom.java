package com.example.javafx.datacreate;


import com.example.javafx.DBuntils.DataBaseService.DB_SickRoomService;
import com.example.javafx.source.doctor.proj.Doctor;
import com.example.javafx.source.sickroom.proj.SickRoom;

import java.sql.SQLException;
import java.util.ArrayList;

public class Data_sickroom {
    private static final RandomDataUntils random=new RandomDataUntils();
    private static final ArrayList<SickRoom> sickRooms=new ArrayList<>();
    private static final ArrayList<Doctor> doctors=new Data_doctor().getDoctors();
    static {
        int index;
        int size=doctors.size();
        for (int i = 0; i < 300; i++) {
            index = random.getNum(size);
            sickRooms.add(new SickRoom(random.getRandomNumber("sickroom"), random.getWardType(), random.getDepartment(), random.getNumber_bed(), random.getRemaing_bed(), doctors.get(index).getDoctor_name()));
        }
        try {
            DB_SickRoomService.addData(sickRooms);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SickRoom> getSickRooms() {
        return sickRooms;
    }
}
