package com.tanghongtu.advanced.p05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by tanghongtu on 2020/7/22.
 */
public class DiskClassLoader extends ClassLoader {

    private String filePath;

    public DiskClassLoader(String path) {
        filePath = path;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] classBytes = null;
        try {
            Path path = new File(filePath).toPath();
            classBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, classBytes, 0, classBytes.length);
    }

}
