package com.pilnyck.filemanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    public static final String DIRECTORY_TEST = "TestDirectory";
    public static final String COPY_FROM = "TestFromCopyDirectory";
    public static final String COPY_TO = "TestToCopyDirectory";
    public static final String EMPTY_DIR = "EmptyDirectory";
    FileManager fileManager = new FileManager();

    @BeforeEach
    void before() throws IOException {
        // 6 folders, 4 files
        File testDir = new File(DIRECTORY_TEST);
        testDir.mkdir();
        //String dirPath = testDir.getPath();
        File file1 = new File(DIRECTORY_TEST,"test1.txt");
        file1.createNewFile();
        File file2 = new File(DIRECTORY_TEST,"test2.txt");
        file2.createNewFile();

        File testDir1 = new File(testDir, "Dir1");
        testDir1.mkdir();
        File testDir11 = new File(testDir1, "Dir11");
        testDir11.mkdir();
        File file11 = new File(testDir11,"test11.txt");
        file11.createNewFile();

        File testDir2 = new File(testDir, "Dir2");
        testDir2.mkdir();
        File file21 = new File(testDir2,"test21.txt");
        file21.createNewFile();

        File testDir3 = new File(testDir, "Dir3");
        testDir3.mkdir();
        File testDir31 = new File(testDir3, "Dir31");
        testDir31.mkdir();

        File testDir4 = new File(testDir, "Dir4");
        testDir4.mkdir();
        File file41 = new File(testDir4,"test41.txt");
        file41.createNewFile();

        File testFromFileDir = new File(COPY_FROM);
        testFromFileDir.mkdir();
        File file5 = new File(testFromFileDir,"test5.txt");
        file5.createNewFile();
        File file6 = new File(testFromFileDir,"test6.txt");
        file6.createNewFile();

        File testToFileDir = new File(COPY_TO);
        testToFileDir.mkdir();

        File testEmptyDir = new File(EMPTY_DIR);
        testEmptyDir.mkdir();
    }

    @AfterEach
    void after() throws IOException {
        File testDir = new File(DIRECTORY_TEST);
        File file1 = new File(DIRECTORY_TEST,"test1.txt");
        file1.delete();
        File file2 = new File(DIRECTORY_TEST,"test2.txt");
        file2.delete();

        File testDir1 = new File(testDir, "Dir1");
        File testDir11 = new File(testDir1, "Dir11");
        File file11 = new File(testDir11,"test11.txt");
        file11.delete();
        testDir11.delete();
        testDir1.delete();

        File testDir2 = new File(testDir, "Dir2");
        File file21 = new File(testDir2,"test21.txt");
        file21.delete();
        testDir2.delete();

        File testDir3 = new File(testDir, "Dir3");
        File testDir31 = new File(testDir3, "Dir31");
        testDir31.delete();
        testDir3.delete();

        File testDir4 = new File(testDir, "Dir4");
        File file41 = new File(testDir4,"test41.txt");
        file41.delete();
        testDir4.delete();

        File testFromFileDir = new File(COPY_FROM);
        File file5 = new File(testFromFileDir,"test5.txt");
        file5.delete();
        File file6 = new File(testFromFileDir,"test6.txt");
        file6.delete();
        testFromFileDir.delete();

        File testToFileDir = new File(COPY_TO);
        testToFileDir.delete();

        File testEmptyDir = new File(EMPTY_DIR);
        testEmptyDir.delete();

        testDir.delete();
    }


    @DisplayName("file count method work correct")
    @Test
    public void testFileCountFilesMethod() {
        int actual = fileManager.countFiles(DIRECTORY_TEST);

        assertEquals(5, actual);
    }


    @DisplayName("file count method work correct on empty directory")
    @Test
    public void testFileCountFilesMethodOnEmptyDirectory() {
        int actual = fileManager.countFiles(EMPTY_DIR);
        int expected = 0;
        assertEquals(expected, actual);
    }


    @DisplayName("directory count method work correct")
    @Test
    public void testDirCountMethod() {
        int actual = fileManager.countDirs(DIRECTORY_TEST);
        assertEquals(6, actual);
    }

    @DisplayName("copy files method work correct")
    @Test
    public void testCopyFilesMethod() throws IOException {
        File file5 = new File(COPY_FROM, "test5.txt");
        assertTrue(file5.exists());

        fileManager.copy(file5.getAbsolutePath(), COPY_TO);

        File file15 = new File(COPY_TO, "test5.txt");
        assertTrue(file15.exists());
    }

    @DisplayName("copy files method work correct")
    @Test
    public void testCopyFilesMethodWithAllInformationInFile() throws IOException {
        File file5 = new File(COPY_FROM, "test5.txt");
        file5.createNewFile();
        assertTrue(file5.exists());
        FileOutputStream fileOutputStream = new FileOutputStream(file5);
        fileOutputStream.write(("Hello Dear Friends!!").getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();
        fileOutputStream.close();

        long expected = file5.length();

        fileManager.copy(file5.getAbsolutePath(), COPY_TO);
        File file6 = new File(COPY_TO, file5.getName());
        assertTrue(file6.exists());

        long actual = file6.length();
        assertEquals(expected, actual);
    }

    @DisplayName("move files method work correct")
    @Test
    public void testMoveFilesMethod() {
        File file1 = new File(DIRECTORY_TEST, "test1.txt");
        fileManager.move(file1.getAbsolutePath(), COPY_TO);
        File movedFile = new File(COPY_TO, "test1.txt");

        assertFalse(file1.exists());
        System.out.println(file1.getAbsolutePath());
        assertTrue(movedFile.exists());;
    }

    @DisplayName("move directories method work correct")
    @Test
    public void testMoveDirMethod() throws IOException {
        File testDir1 = new File(DIRECTORY_TEST, "Dir1");

        assertTrue(testDir1.isDirectory());
        assertTrue(testDir1.exists());

        fileManager.moveDirs(testDir1.getAbsolutePath(), COPY_TO);
        File movedDir = new File(COPY_TO, "Dir1");
        assertTrue(movedDir.exists());
        assertFalse(testDir1.exists());
    }
}
