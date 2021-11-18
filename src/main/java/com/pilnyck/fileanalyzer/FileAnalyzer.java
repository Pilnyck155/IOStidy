package com.pilnyck.fileanalyzer;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    public FileStatistics analyzer(String path, String keyWord){
        checkCorrectPath(path);
        String content = getContent(path);
        List<String> sentences = hackToSentences(content);
        List<String> sentencesWithKeyWord = finder(sentences, keyWord);
        int count = countWord(sentencesWithKeyWord);
        return new FileStatistics(sentencesWithKeyWord, count);
    }


    //Write error if file not founded
    public void checkCorrectPath(String path){
        File file = new File(path);
        if (!file.exists()){
            try {
                throw new FileNotFoundException("File not found!!!!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getContent(String path){
        byte[] byteArray = null;
        try(FileInputStream fileInputStream = new FileInputStream(path)){
            byteArray = fileInputStream.readAllBytes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new String(byteArray);
    }

    public List<String> hackToSentences(String  content){
        String[] contentArray = content.split(("\\s*(\\s|\\.|!|\\?)\\s"));
        List<String> sentences = new LinkedList<>();
        for (String s : contentArray) {
            sentences.add(s);
        }
        return sentences;
    }

    public List<String> finder(List<String> sentences, String keyWord){
        List<String> sentencesWithKeyWord = new LinkedList<>();
        Pattern pattern = Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);

        for (String s : sentences) {
            Matcher matcher = pattern.matcher(s);
            boolean found = matcher.find();
            if (found){
                sentencesWithKeyWord.add(s);
            }
        }
        return sentencesWithKeyWord;
    }

    private int countWord(List<String> sentencesWithKeyWord){
        return sentencesWithKeyWord.size();
    }
}







