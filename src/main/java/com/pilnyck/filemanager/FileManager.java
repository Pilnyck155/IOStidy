package com.pilnyck.filemanager;

import java.io.*;

public class FileManager {
    private static int counterFiles = 0;
    private static int counterDirectories = 0;

    // принимает путь к папке,
    //возвращает количество файлов в папке и всех подпапках по пути
    public int countFiles(String path) {
        if (path != "") {
            File file = new File(path);
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File listFile : listFiles) {
                    if (listFile.isFile()) {
                        counterFiles++;
                    } else {
                        countFiles(listFile.getPath());
                    }
                }
            }
        }
        return counterFiles;
    }


    //- принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public int countDirs(String path) {
        if (path != "") {
            File file = new File(path);
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File listFile : listFiles) {
                    if (listFile.isDirectory()) {
                        counterDirectories++;
                        countDirs(listFile.getPath());
                    }
                }
            }
        }
        return counterDirectories;
    }

    //- метод по копированию папок и файлов.
    //Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    public void copy(String from, String to) throws IOException {
        File file = new File(from);
        if (file.exists()) {
            File fileTo = new File(to, file.getName());
            if (file.isFile()) {
                fileTo.createNewFile();
                if (fileTo.isDirectory()) {
                    fileTo.createNewFile();
                }
                try (BufferedInputStream bufferedInputStream =
                             new BufferedInputStream(new FileInputStream(file.getPath()));
                     BufferedOutputStream bufferedOutputStream =
                             new BufferedOutputStream(new FileOutputStream(fileTo.getPath()))) {
                    byte[] byteArray = bufferedInputStream.readAllBytes();
                    bufferedOutputStream.write(byteArray);
                }
            } else {
                copyDir(file.getAbsolutePath(), fileTo.getAbsolutePath());
            }
        }
    }

    //- метод по перемещению папок и файлов.
    //Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    public void move(String from, String to) {
        File fileFrom = new File(from);
        File fileTo = new File(to, fileFrom.getName());
        fileFrom.renameTo(new File(fileTo.getAbsolutePath()));
    }

    public void moveDirs(String from, String to) throws IOException {
        File dirFrom = new File(from);
        File dirTo = new File(to, dirFrom.getName());
        if (!dirTo.exists()) {
            dirTo.mkdir();
        }
        File[] list = dirFrom.listFiles();
        if (list != null) {
            for (File listOfFile : list) {
                if (listOfFile.isFile()) {
                    move(listOfFile.getAbsolutePath(), dirTo.getAbsolutePath());
                } else {
                    moveDirs(listOfFile.getAbsolutePath(), dirTo.getAbsolutePath());
                    listOfFile.delete();
                }
            }
        }
        dirFrom.delete();
    }

    private void copyDir(String from, String to) throws IOException {
        File file = new File(from);
        if (file.exists()) {
            File fileTo = new File(to);
            fileTo.mkdir();
            File[] files = file.listFiles();
            if (file != null) {
                for (File file1 : files) {
                    if (file1.isFile()) {
                        copy(file1.getAbsolutePath(), fileTo.getAbsolutePath());
                    } else {
                        copyDir(file1.getAbsolutePath(), fileTo.getAbsolutePath());
                    }
                }
            }
        }
    }
}