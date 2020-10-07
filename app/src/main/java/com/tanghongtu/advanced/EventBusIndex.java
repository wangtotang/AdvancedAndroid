package com.tanghongtu.advanced;

import com.tang.eventbus.SubscribeIndex;
import com.tang.eventbus.SubscribeMethod;
import com.tang.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanghongtu
 * @date 2020/9/29
 */
public class EventBusIndex implements SubscribeIndex {

    private static final Map<Class, List<SubscribeMethod>> CACHE_MAP = new HashMap<>();

    static {
        List<SubscribeMethod> methods = new ArrayList<>();
        methods.add(new SubscribeMethod(MainActivity.class, "onReceived", String.class, ThreadMode.ASYNC));
        CACHE_MAP.put(MainActivity.class, methods);
    }

    @Override
    public Map<Class, List<SubscribeMethod>> getSubscribeMethodMap() {
        return CACHE_MAP;
    }
}
