package com.pilnyck.filemanager;

import java.io.*;
import java.util.Arrays;
import java.util.StringJoiner;

// /home/vitaliu/IdeaProjects/IOStudy/src/main/resources/test7.txt

public class StartFileManager {
    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        System.out.printf("Choose action, that you want. If you need help, enter help: ");
        String action = getString();
        anAction(action);
    }


    private static String getString() {
        //String action = null;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            //action = bufferedReader.readLine();
            stringBuilder.append(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void anAction(String action) {
        if (action.equalsIgnoreCase("help")) {
            System.out.println("To count folders in directory enter COUNT FILES and enter path to directory!");
            System.out.println("To count directories in directory enter COUNT DIRS and enter path to directory!");
            System.out.println("To copy folder or directory enter COPY and enter path to directories!");
            System.out.println("To move folder or directory enter MOVE and enter path to directories!");
            getString();

        } else if (action.equalsIgnoreCase("COUNT FILES")) {
            FileManager fileManager = new FileManager();
            String pathToFile = getString();
            int countFiles = fileManager.countFiles(pathToFile);
            System.out.printf("Count files: ", +countFiles);

        } else if (action.equalsIgnoreCase("COUNT DIRS")) {
            FileManager fileManager = new FileManager();
            String pathToFile = getString();
            int countDirs = fileManager.countDirs(pathToFile);
            System.out.printf("Count directories: ", +countDirs);

        } else if (action.equalsIgnoreCase("COPY")) {
            FileManager fileManager = new FileManager();
            System.out.println("Enter path to file/folder you want to copy: ");
            String pathFrom = getString();
            System.out.println();
            System.out.println("Enter path to file/folder were you want to copy:");
            String pathTo = getString();
            try {
                fileManager.copy(pathFrom, pathTo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("MOVE")) {
            FileManager fileManager = new FileManager();
            System.out.println("Enter path to file/folder you want to move: ");
            String pathFrom = getString();
            System.out.println("Enter path to file/folder were you want to move: ");
            String pathTo = getString();
            fileManager.move(pathFrom, pathTo);

        } else {
            System.out.println("You enter incorrect data!");
        }
    }
}

