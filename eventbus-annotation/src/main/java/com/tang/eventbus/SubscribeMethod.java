package com.tang.eventbus;

import java.lang.reflect.Method;

/**
 * @author tanghongtu
 * @date 2020/9/29
 */
public class SubscribeMethod {

    public Method declaredMethod;
    public Class<?> parameterType;
    public ThreadMode threadMode;
    public String methodName;
    public Class<?> subscribeClz;

    public SubscribeMethod(Class<?> subscribeClz, String methodName, Class<?> parameterType, ThreadMode threadMode) {
        this.subscribeClz = subscribeClz;
        this.methodName = methodName;
        this.parameterType = parameterType;
        this.threadMode = threadMode;
    }

    public SubscribeMethod(Method declaredMethod, Class<?> parameterType, ThreadMode threadMode) {
        this.declaredMethod = declaredMethod;
        this.parameterType = parameterType;
        this.threadMode = threadMode;
    }

    public Method getDeclaredMethod(){
        if(declaredMethod == null) {
            try {
                declaredMethod = subscribeClz.getMethod(methodName, parameterType);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return declaredMethod;
    }
}
