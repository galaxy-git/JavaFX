package com.example.javafx.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

interface FileUntils <T>{
    File file=new File("C:\\Users\\asus\\Desktop");
    ArrayList<T> file_read() throws IOException;
    void file_write(ArrayList<T> ts) throws IOException;
    boolean getType();
}
