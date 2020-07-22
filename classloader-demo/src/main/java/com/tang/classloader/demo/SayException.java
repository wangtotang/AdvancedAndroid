package com.tang.classloader.demo;

/**
 * Created by tanghongtu on 2020/7/22.
 */
public class SayException implements ISay{

    @Override
    public String saySomething() {
        return "something is wrong";
    }
}
