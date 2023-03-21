package com.example.javafx.file;

import com.example.javafx.source.doctor.proj.Doctor;

import java.io.*;
import java.util.ArrayList;

public class File_doctor implements FileUntils{
    private final File file_doctor=new File(file+File.separator+"doctor.txt");

    @Override
    public ArrayList<Doctor> file_read() throws IOException{
        InputStream is=new FileInputStream(file_doctor);
        ArrayList<Doctor> doctorArrayList=new ArrayList<>();
        try(ObjectInputStream ois=new ObjectInputStream(is)) {
            while (ois.available()!=0) {
                doctorArrayList.add((Doctor) ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doctorArrayList;
    }

    @Override
    public void file_write(ArrayList doctors) throws IOException{
        OutputStream os=new FileOutputStream(file_doctor);
        try(ObjectOutputStream oos=new ObjectOutputStream(os)){
            for (Object doctor:doctors){
                oos.writeObject(doctor);
            }
        }
    }

    @Override
    public boolean getType() {
        return file_doctor.exists();
    }
}
