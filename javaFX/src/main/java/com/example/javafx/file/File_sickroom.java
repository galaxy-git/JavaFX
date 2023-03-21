package com.example.javafx.file;

import com.example.javafx.source.sickroom.proj.SickRoom;

import java.io.*;
import java.util.ArrayList;

public class File_sickroom implements FileUntils{
    private final File file_sickroom=new File(file+File.separator+"sickroom.txt");

    @Override
    public ArrayList<SickRoom> file_read() throws IOException {
        InputStream is=new FileInputStream(file_sickroom);
        ArrayList<SickRoom> sickRoomArrayList=new ArrayList<>();
        try(ObjectInputStream ois=new ObjectInputStream(is)) {
            while (ois.available()!=0) {
                sickRoomArrayList.add((SickRoom) ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sickRoomArrayList;
    }

    @Override
    public void file_write(ArrayList sickRooms) throws IOException{
        OutputStream os=new FileOutputStream(file_sickroom);
        try(ObjectOutputStream oos=new ObjectOutputStream(os)){
            for (Object sickRoom:sickRooms){
                oos.writeObject(sickRoom);
            }
        }
    }

    @Override
    public boolean getType() {
        return file_sickroom.exists();
    }
}
