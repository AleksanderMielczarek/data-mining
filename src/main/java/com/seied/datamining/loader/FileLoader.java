package com.seied.datamining.loader;

import java.io.File;

/**
 * Created by Janek on 2014-11-15.
 */
public class FileLoader {
    private File file;

    public FileLoader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        this.file = new File(classLoader.getResource(fileName).getFile());
    }

    public File getFile() {
        return file;
    }
}
