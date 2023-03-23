package com.example.javafx.file;

import com.example.javafx.source.nurse.proj.Nurse;

import java.io.*;
import java.util.ArrayList;

public class File_nurse implements FileUntils{
    private final File file_nurse=new File(file+File.separator+"nurse.txt");

    @Override
    public ArrayList<Nurse> file_read() throws IOException {
        InputStream is=new FileInputStream(file_nurse);
        ArrayList<Nurse> nurseArrayList=new ArrayList<>();
        try(ObjectInputStream ois=new ObjectInputStream(is)) {
            while (ois.available()!=0) {
                nurseArrayList.add((Nurse) ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nurseArrayList;
    }

    @Override
    public void file_write(ArrayList nurses) throws IOException{
        OutputStream os=new FileOutputStream(file_nurse);
        try(ObjectOutputStream oos=new ObjectOutputStream(os)){
            for (Object nurse:nurses){
                oos.writeObject(nurse);
            }
        }
    }

    @Override
    public boolean getType() {
        return file_nurse.exists();
    }
}
