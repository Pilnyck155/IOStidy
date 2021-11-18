package com.pilnyck.fileanalyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {
    FileAnalyzer fileAnalyzer = new FileAnalyzer();

    @DisplayName("test analyzer method")
    @Test
    public void testAnalyzerMethod() {
        String path = "/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test4.txt";
        String keyWord = "Dursley";

        FileStatistics fileStatistics = fileAnalyzer.analyzer(path, keyWord);
        int actualWordCount = fileStatistics.wordCount;
        int expectedCount = 9;
        List<String> actualSentencesWithKey = fileStatistics.getSentences();
        assertEquals(expectedCount, actualWordCount);
    }

    @DisplayName("test correct path was entered")
    @Test
    public void iTestCheckCorrectPathMethod() {
        String path = "/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt";
        File file = new File(path);
        boolean expected = file.exists();
        assertTrue(expected);

        fileAnalyzer.checkCorrectPath(path);
    }

    @DisplayName("test incorrect path was entered and throw exception")
    @Test
    public void iTestCheckCorrectPath_AndThrowException_IfFileNotExist() {
        String wrongPath = "/home/vitaliu/IdeaProjects/IOStudy/src/test/test_f.txt";
        File file = new File(wrongPath);
        boolean expected = file.exists();
        assertFalse(expected);

        fileAnalyzer.checkCorrectPath(wrongPath);
    }

    @DisplayName("test get content from file")
    @Test
    public void testGetContent() {
        String actual = fileAnalyzer.getContent("/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt");
        String expected = "London is a capital of Great Britain!";
        assertEquals(expected, actual);
    }

    @DisplayName("test correct hack content to sentences")
    @Test
    public void testHackToSentences() {
        String content = "London is a capital of Great Britain! We all yong? Who is on duty today?";
        List<String> actual = fileAnalyzer.hackToSentences(content);
        List<String> expected = new LinkedList<>();
        expected.add("London is a capital of Great Britain");
        expected.add("We all yong");
        expected.add("Who is on duty today?");
        assertEquals(expected, actual);
    }

    @DisplayName("test find key word in List<String>")
    @Test
    public void testFinder() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("London is a capital of Great Britain");
        linkedList.add("We all yong");
        linkedList.add("Who is on duty today?");

        String keyWord = "is";
        List<String> actual = fileAnalyzer.finder(linkedList, keyWord);

        List<String> expected = new LinkedList<>();
        expected.add("London is a capital of Great Britain");
        expected.add("Who is on duty today?");
        assertEquals(expected, actual);
    }
}
