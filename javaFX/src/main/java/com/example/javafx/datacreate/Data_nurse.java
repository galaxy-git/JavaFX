package com.example.javafx.datacreate;

import com.example.javafx.DBuntils.DataBaseService.DB_NurseService;
import com.example.javafx.source.nurse.proj.Nurse;
import com.example.javafx.source.sickroom.proj.SickRoom;

import java.sql.SQLException;
import java.util.ArrayList;

public class Data_nurse {
    private static final RandomDataUntils random=new RandomDataUntils();
    private static final ArrayList<Nurse> nurses=new ArrayList<>();
    private static final ArrayList<SickRoom> sickRooms=new Data_sickroom().getSickRooms();
    static {
        int index;
        int size=sickRooms.size();
        for (int i=0;i<600;i++){
            index=random.getNum(size);
            nurses.add(new Nurse(random.getRandomNumber("nurse"), random.getRandomName(), random.name_sex, random.getTitle(), sickRooms.get(index).getWardNo()));
        }
        try {
            DB_NurseService.addData(nurses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
