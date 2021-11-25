package com.pilnyck.filemanager;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilterTest implements FilenameFilter{
   String extract = ".";

    public FileFilterTest(String extract) {
        this.extract = extract;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extract);
    }
}
