package com.pilnyck.fileanalyzer;

import java.io.*;
import java.util.List;
/*
To test program enter next line in console:
/home/vitaliu/IdeaProjects/IOStudy/src/main/resources/test3.txt java

 */

public class StarFileAnalyzer {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter path and key word: ");
        String[] pathAndKeyWord = getPathAndKey();
        String path = pathAndKeyWord[0];
        String keyWord = pathAndKeyWord[1];




        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        FileStatistics fileStatistics = fileAnalyzer.analyzer(path, keyWord);
        printSentencesWithKeyWord(fileStatistics.getSentences());
        printCount(fileStatistics.wordCount);
    }



    static String[] getPathAndKey() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {
            String pathAndKeyWord = bufferedReader.readLine();
            String[] splitString = pathAndKeyWord.split(" ");
            return splitString;
        }
    }



    static void printSentencesWithKeyWord(List<String> sentencesWithKeyWord){
        for (String s : sentencesWithKeyWord) {
            System.out.println(s);
        }
    }

    static void printCount(int countWord){
        System.out.println("Key word repeated: " + countWord);
    }
}
