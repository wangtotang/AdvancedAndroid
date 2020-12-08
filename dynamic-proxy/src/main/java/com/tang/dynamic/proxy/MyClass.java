package com.tang.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyClass {

    public static void main(String[] args) {

        MyInterface service1 = new ProxyService().createService();
        service1.test1();
        service1.test2();
    }

}