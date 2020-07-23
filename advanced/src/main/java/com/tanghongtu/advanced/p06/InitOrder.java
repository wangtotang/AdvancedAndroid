package com.tanghongtu.advanced.p06;

/**
 * Created by tanghongtu on 2020/7/23.
 */
public class InitOrder {

    public static void main(String[] args) {
        Parent p = new Child();
        System.out.println("--------------------------------");
        p = new Child();
    }

    static class Child extends Parent {
        static {
            System.out.println("Child static block!");
        }
        {
            System.out.println("Child non-static block!");
        }
        public Child() {
            System.out.println("Child constructor!");
        }
    }

    static class Parent {
        static {
            System.out.println("Parent static block!");
        }
        {
            System.out.println("Parent non-static block!");
        }
        public Parent() {
            System.out.println("Parent constructor!");
        }
    }

}
