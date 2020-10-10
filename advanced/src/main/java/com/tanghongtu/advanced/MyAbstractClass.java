package com.tanghongtu.advanced;

/**
 * @author tanghongtu
 * @date 2020/10/10
 */
public abstract class MyAbstractClass implements MyInterface {

//    int i = 10;

    @Override
    public void test2() {
        System.out.println("test2");
    }

    public void test(){
        System.out.println("MyAbstractClass");
    }
}
