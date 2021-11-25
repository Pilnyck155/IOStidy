package com.pilnyck.filemanager;

import com.pilnyck.fileanalyzer.FileStatistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    public static final String pathForTestDir = "DirectoryTest";
    public static final String pathFromCopyAndMoveFiles = "DirectoryTest/FilesDirectoryTestFromCopyAndMove";
    public static final String pathToCopyAndMoveFiles = "DirectoryTest/FilesDirectoryTestToCopyAndMove";
    public static final String pathEmptyDir = "DirectoryTest/EmptyDirectoryTest";

    @BeforeEach
    void before() throws IOException {
        //FileManager fileManager = new FileManager();

        File testDir = new File(pathForTestDir);
        testDir.mkdir();
        String dirPath = testDir.getPath();
        File file1 = new File(dirPath,"test1.txt");
        file1.createNewFile();
        File file2 = new File(dirPath,"test2.txt");
        file2.createNewFile();

        File testDir1 = new File(testDir, "Dir1");
        testDir1.mkdir();
        File testDir11 = new File(testDir1, "Dir11");
        testDir11.mkdir();
        File testDir12 = new File(testDir1, "Dir12");
        testDir12.mkdir();
        File file11 = new File(testDir1,"test11.txt");
        file11.createNewFile();

        File testDir2 = new File(testDir, "Dir2");
        testDir2.mkdir();
        File file21 = new File(testDir2,"test21.txt");
        file21.createNewFile();

        File testDir3 = new File(testDir, "Dir3");
        testDir3.mkdir();
        File testDir31 = new File(testDir3, "Dir31");
        testDir31.mkdir();
        File testDir32 = new File(testDir3, "Dir32");
        testDir32.mkdir();

        File testDir4 = new File(testDir, "Dir4");
        testDir4.mkdir();
        File file41 = new File(testDir4,"test41.txt");
        file41.createNewFile();
        File file42 = new File(testDir4,"test42.txt");
        file42.createNewFile();

        File testFromFileDir = new File(pathFromCopyAndMoveFiles);
        testFromFileDir.mkdir();
        File file5 = new File(testFromFileDir,"test5.txt");
        file5.createNewFile();

        File testToFileDir = new File(pathToCopyAndMoveFiles);
        testToFileDir.mkdir();

        File testEmptyDir = new File(pathEmptyDir);
        testEmptyDir.mkdir();
    }

    @AfterEach
    void after() throws IOException {
        File testDir = new File(pathForTestDir);

        File testDir4 = new File(testDir, "Dir4");
        File file41 = new File(testDir4,"test41.txt");
        file41.delete();
        File file42 = new File(testDir4,"test42.txt");
        file42.delete();
        testDir4.delete();

        File testDir3 = new File(testDir, "Dir3");
        File testDir31 = new File(testDir3, "Dir31");
        testDir31.delete();
        File testDir32 = new File(testDir3, "Dir32");
        testDir32.delete();
        testDir3.delete();

        File testDir2 = new File(testDir, "Dir2");
        File file21 = new File(testDir2,"test21.txt");
        file21.delete();
        testDir2.delete();

        File testDir1 = new File(testDir, "Dir1");
        File testDir11 = new File(testDir1, "Dir11");
        testDir11.delete();
        File testDir12 = new File(testDir1, "Dir12");
        testDir12.delete();
        File file11 = new File(testDir1,"test11.txt");
        file11.delete();
        testDir1.delete();

        String dirPath = testDir.getPath();
        File file1 = new File(dirPath,"test1.txt");
        file1.delete();
        File file2 = new File(dirPath,"test2.txt");
        file2.delete();
        testDir.delete();

        File testFromFileDir = new File(pathFromCopyAndMoveFiles);
        testFromFileDir.delete();
        File file5 = new File(testFromFileDir,"test5.txt");
        file5.delete();

        File testToFileDir = new File(pathToCopyAndMoveFiles);
        testToFileDir.delete();

        File testEmptyDir = new File(pathEmptyDir);
        testEmptyDir.delete();

        testDir.delete();
    }


    @DisplayName("file count method work correct")
    @Test
    public void testFileCountFilesMethod() {
        FileManager fileManager = new FileManager();
        int actual = fileManager.countFiles(pathForTestDir);

        assertEquals(10, actual);
    }


    @DisplayName("file count method work correct on empty directory")
    @Test
    public void testFileCountFilesMethodOnEmptyDirectory() {
        FileManager fileManager = new FileManager();

        int actual = fileManager.countFiles(pathEmptyDir);
        int expected = 0;
        assertEquals(expected, actual);
    }


    @DisplayName("directory count method work correct")
    @Test
    public void testDirCountMethod() {
        FileManager fileManager = new FileManager();
        int actual = fileManager.countDirs(pathForTestDir);

        assertEquals(11, actual);
    }

    @DisplayName("copy files method work correct")
    @Test
    public void testCopyFilesMethod() throws IOException {
        FileManager fileManager = new FileManager();

        File file5 = new File(pathFromCopyAndMoveFiles, "test5.txt");

        assertTrue(file5.exists());


        File testToFileDir = new File(pathToCopyAndMoveFiles);
        testToFileDir.exists();

        fileManager.copy(file5.getAbsolutePath(), testToFileDir.getPath());

        File file15 = new File(testToFileDir, "test5.txt");
        assertTrue(file15.exists());
    }

    @DisplayName("copy files method work correct")
    @Test
    public void testCopyFilesMethodWithAllInformationInFile() throws IOException {
        FileManager fileManager = new FileManager();
        File file11 = new File(pathForTestDir, "test11.txt");
        file11.createNewFile();
        assertTrue(file11.exists());
        FileOutputStream fileOutputStream = new FileOutputStream(file11);
        fileOutputStream.write(("Hello IO").getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();
        fileOutputStream.close();

        long expected = file11.length();

        fileManager.copy(file11.getAbsolutePath(), pathToCopyAndMoveFiles);
        File file6 = new File(pathToCopyAndMoveFiles, file11.getName());
        assertTrue(file6.exists());

        long actual = file6.length();
        assertEquals(expected, actual);
    }

    @DisplayName("move files method work correct")
    @Test
    public void testMoveFilesMethod() {
        FileManager fileManager = new FileManager();
        File file = new File(pathFromCopyAndMoveFiles, "test11.txt");
        fileManager.move(file.getPath(), pathToCopyAndMoveFiles);

        File movedFile = new File(pathToCopyAndMoveFiles, "test11.txt");

        assertFalse(file.exists());
        assertTrue(movedFile.exists());;
    }
}
