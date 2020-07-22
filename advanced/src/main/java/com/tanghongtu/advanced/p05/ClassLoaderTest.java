package com.tanghongtu.advanced.p05;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tanghongtu on 2020/7/22.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        System.out.println("cl is " + cl);

        ClassLoader parent = cl.getParent();
        System.out.println("parent is " + parent);

        ClassLoader boot_strap = parent.getParent();
        System.out.println("boot_strap is " + boot_strap);
    }

    @Test
    public void testClassLoader() {
        DiskClassLoader diskClassLoader = new DiskClassLoader("E:\\AndroidWorkspace\\AdvancedAndroid\\advanced\\build\\classes\\java\\main\\com\\tanghongtu\\advanced\\p05\\Secret.class");
        try {
            Class c = diskClassLoader.loadClass("com.tanghongtu.advanced.p05.Secret");
            if (c != null) {
                Object obj = c.newInstance();
                Method method = c.getDeclaredMethod("printSecret", null);
                method.invoke(obj, null);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
