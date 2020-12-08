package com.tang.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyService {

    MyInterface target;

    public ProxyService(){
        String s = "SDK3";
        switch (s) {
            case "SDK1":
                target = new SDK1();
                break;
            case "SDK2":
                target = new SDK2();
                break;
            case "SDK3":
                target = new SDK3();
                break;
        }
    }

    public MyInterface createService(){
        return (MyInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
                return method.invoke(target, args);
            }
        });
    }

}
