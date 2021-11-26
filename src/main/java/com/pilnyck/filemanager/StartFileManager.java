package com.pilnyck.filemanager;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

// /home/vitaliu/IdeaProjects/IOStudy/src/main/resources/test7.txt
///home/vitaliu/Documents/Horstman.pdf

public class StartFileManager {
    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        System.out.printf("Choose action, that you want. If you need help, enter help: ");
        //String action = fileManager.getString();
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        //System.out.println("You choose: " + action + " !");
        anAction(action);
        //System.out.println(fileManager.countDirs(action));
    }

    private static void anAction(String action) throws IOException {
        FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);
        if (action.equalsIgnoreCase("help")) {
            System.out.println("To count folders in directory enter COUNT FILES and enter path to directory!");
            System.out.println("To count directories in directory enter COUNT DIRS and enter path to directory!");
            System.out.println("To copy folder or directory enter COPY and enter path to directories!");
            System.out.println("To move folder or directory enter MOVE and enter path to directories!");
            String result = scanner.nextLine();
            anAction(result);

        } else if (action.equalsIgnoreCase("COUNT FILES")) {
            String pathToFile = scanner.nextLine();
            int countFiles = fileManager.countFiles(pathToFile);
            System.out.printf("Count files: ", + countFiles);

        } else if (action.equalsIgnoreCase("COUNT DIRS")) {
            String pathToFile = scanner.nextLine();
            int countDirs = fileManager.countDirs(pathToFile);
            System.out.printf("Count directories: ", +countDirs);

        } else if (action.equalsIgnoreCase("COPY")) {
            System.out.println("Enter path to file/folder you want to copy: ");
            String pathFrom = scanner.nextLine();
            System.out.println();
            System.out.println("Enter path to file/folder were you want to copy:");
            String pathTo = scanner.nextLine();
            try {
                fileManager.copy(pathFrom, pathTo);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("MOVE")) {
            System.out.println("Enter path to file/folder you want to move: ");
            String pathFrom = scanner.nextLine();
            System.out.println("Enter path to file/folder were you want to move: ");
            String pathTo = scanner.nextLine();
            fileManager.move(pathFrom, pathTo);

        } else {
            System.out.println("You enter incorrect data!");
        }
    }
}

