package com.tang.classloader.demo;

/**
 * Created by tanghongtu on 2020/7/22.
 */
public class SayHotFix implements ISay{

    @Override
    public String saySomething() {
        return "everything is right";
    }
}
