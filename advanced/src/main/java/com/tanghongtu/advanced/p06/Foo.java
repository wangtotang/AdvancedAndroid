package com.tanghongtu.advanced.p06;

/**
 * Created by tanghongtu on 2020/7/23.
 */
public class Foo {

    public static void main(String[] args) {
        new Foo().print();
    }

    public void print() {
        int superCode = super.hashCode();
        System.out.println("superCode is " + superCode);

        int thisCode = hashCode();
        System.out.println("thisCode is " + thisCode);
    }

    @Override
    public int hashCode() {
        return 111;
    }
}
