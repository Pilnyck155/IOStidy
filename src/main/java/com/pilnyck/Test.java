package com.pilnyck;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test{
    public static void main(String[] args) throws IOException {

        File path2 = new File("/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt");
        System.out.println(path2.exists());
        //System.out.println(System.getProperties());
        if (path2.isDirectory()){
            File[] listFiles = path2.listFiles();
            for (File listFile : listFiles) {
                System.out.println(listFile);
            }
        } else {
            System.out.println("File: " + path2.isFile());
        }
    }

    static String readContent(String path) throws IOException{
        File pathFile = new File(path);
        InputStream inputStream = new FileInputStream(pathFile);

        int fileLength = (int) pathFile.length();
        byte[] contentArray = new byte[fileLength];

        inputStream.read(contentArray);
        return new String(contentArray);
    }

    static void writeContent(String path, String content) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        outputStream.write(contentBytes);
    }
}
