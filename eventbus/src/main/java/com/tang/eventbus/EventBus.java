package com.tang.eventbus;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tanghongtu
 * @date 2020/9/29
 */
public class EventBus {

    private static EventBus instance;
    private Map<Class<?>, List<SubscribeMethod>> cacheMap;
    private Map<Object, List<SubscribeMethod>> subscribeMethodMap;
    private Handler mHandler;
    private ExecutorService cacheThreadPool;

    private EventBus() {
        cacheMap = new HashMap<>();
        subscribeMethodMap = new ConcurrentHashMap<>();
        mHandler = new Handler(Looper.getMainLooper());
        cacheThreadPool = Executors.newCachedThreadPool();
    }

    public static EventBus getDefault() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    public void register(Object obj) {
        if (obj == null) {
            throw new RuntimeException("subscriber can not null");
        }
        Class<?> clz = obj.getClass();
        List<SubscribeMethod> methods = cacheMap.get(clz);
        if(methods == null) {
            methods = getMethodUseInReflection(clz);
            cacheMap.put(clz, methods);
        }
        subscribeMethodMap.put(obj, methods);
    }

    public void addIndex(SubscribeIndex index){
        cacheMap.putAll(index.getSubscribeMethodMap());
    }

    private List<SubscribeMethod> getMethodUseInReflection(Class<?> clz) {
        List<SubscribeMethod> result = new CopyOnWriteArrayList<>();
        while (clz != null) {
            String name = clz.getName();
            if (name.startsWith("java.") | name.startsWith("javax.") | name.startsWith("android.")
                    | name.startsWith("androidx.")) {
                break;
            }
            Method[] declaredMethods = clz.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                Subscribe annotation = declaredMethod.getAnnotation(Subscribe.class);
                if (annotation != null) {
                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        throw new RuntimeException("parameter is more than one");
                    }
                    ThreadMode threadMode = annotation.threadMode();
                    SubscribeMethod method = new SubscribeMethod(declaredMethod, parameterTypes[0], threadMode);
                    result.add(method);
                }
            }
            clz = clz.getSuperclass();
        }
        return result;
    }

    public void post(final Object obj) {
        Set<Object> keySet = subscribeMethodMap.keySet();
        for (final Object subscriber : keySet) {
            List<SubscribeMethod> methods = subscribeMethodMap.get(subscriber);
            for (final SubscribeMethod method : methods) {
                if (method.parameterType.isAssignableFrom(obj.getClass())) {
                    switch (method.threadMode) {
                        case POSTING:
                            invoke(subscriber, method, obj);
                            break;
                        case MAIN:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(subscriber, method, obj);
                            } else {
                                runOnUIThread(obj, subscriber, method);
                            }
                            break;
                        case MAIN_ORDERED:
                            runOnUIThread(obj, subscriber, method);
                            break;
                        case BACKGROUND:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                runOnWorkThread(obj, subscriber, method);
                            } else {
                                invoke(subscriber, method, obj);
                            }
                            break;
                        case ASYNC:
                            runOnWorkThread(obj, subscriber, method);
                            break;
                    }
                }
            }
        }
    }

    private void runOnWorkThread(final Object obj, final Object subscriber, final SubscribeMethod method) {
        cacheThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                invoke(subscriber, method, obj);
            }
        });
    }

    private void runOnUIThread(final Object obj, final Object subscriber, final SubscribeMethod method) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invoke(subscriber, method, obj);
            }
        });
    }

    private void invoke(Object subscriber, SubscribeMethod method, Object obj) {
        try {
            method.getDeclaredMethod().setAccessible(true);
            method.getDeclaredMethod().invoke(subscriber, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void unregister(Object obj) {
        List<SubscribeMethod> methods = subscribeMethodMap.get(obj);
        if (methods == null) {
            throw new RuntimeException("subscriber is not registered yet");
        }
        subscribeMethodMap.remove(obj);
    }

    public void clear(){
        subscribeMethodMap.clear();
        Set<Map.Entry<Class<?>, List<SubscribeMethod>>> entries = cacheMap.entrySet();
        for (Map.Entry<Class<?>, List<SubscribeMethod>> entry : entries) {
            entry.getValue().clear();
        }
        cacheMap.clear();
    }
}
