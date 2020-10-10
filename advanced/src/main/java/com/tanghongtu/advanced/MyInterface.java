package com.tanghongtu.advanced;


/**
 * @author tanghongtu
 * @date 2020/10/10
 */
interface MyInterface {

    int i = 12;

    void test2();

    default void test(){
        System.out.println("MyInterface");
    }

}
