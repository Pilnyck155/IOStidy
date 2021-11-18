package com.pilnyck.fileanalyzer;
/*
Используем классы FileInputStream, FileOutputStream, File
Практика:
1: Написать программу FileAnalyzer, которая в консоли принимает 2 параметра:
1) путь к файлу
2) слово
Usage:
java FileAnalyzer C:/test/story.txt duck

Выводит:
1) Кол-во вхождений искомого слова в файле
2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!").
Каждое предложение выводится с новой строки.


2: Пишем class FileManager с методами
public class FileManager {
// public static int countFiles(String path) - принимает путь к папке,
// возвращает количество файлов в папке и всех подпапках по пути
public static int countFiles(String path) {
return 0;
}

// public static int countDirs(String path) - принимает путь к папке,
// возвращает количество папок в папке и всех подпапках по пути
public static int countDirs(String path) {
return 0;
}
}

public static void copy(String from, String to) - метод по копированию папок и файлов.
Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
public static void move(String from, String to) - метод по перемещению папок и файлов.
Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {

//    @BeforeEach
//    public void before(){
//        FileAnalyzer fileAnalyzer = new FileAnalyzer();
//    }


    @Test
    public void iTestCheckCorrectPath_AndDontThrowException_IfPathIsCorrect(){
        String path = "/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt";
        File file = new File(path);
        boolean expected = file.exists();
        assertTrue(expected);

        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.checkCorrectPath(path);
    }

    @Test
    public void iTestCheckCorrectPath_AndThrowException_IfFileNotExist(){
        String wrongPath = "/home/vitaliu/IdeaProjects/IOStudy/src/test/test_f.txt";
        File file = new File(wrongPath);
        boolean expected = file.exists();
        assertFalse(expected);

        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.checkCorrectPath(wrongPath);
    }

    @Test
    public void testGetContent(){
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        String actual = fileAnalyzer.getContent("/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt");
        //String actual = ("/home/vitaliu/IdeaProjects/IOStudy/src/test/resources/test_f.txt");
        String expected = "London is a capital of Great Britain!";
        assertEquals(expected, actual);
    }

    @Test
    public void testHackToSentences(){
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        String content = "London is a capital of Great Britain! We all yong? Who is on duty today?";
        List<String> actual = fileAnalyzer.hackToSentences(content);
        List<String> expected = new LinkedList<>();
        expected.add("London is a capital of Great Britain");
        expected.add("We all yong");
        expected.add("Who is on duty today?");
        assertEquals(expected, actual);
    }

    @Test
    public void testFinder(){
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
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
