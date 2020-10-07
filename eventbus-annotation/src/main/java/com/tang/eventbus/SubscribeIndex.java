package com.tang.eventbus;

import java.util.List;
import java.util.Map;

/**
 * @author tanghongtu
 * @date 2020/9/29
 */
public interface SubscribeIndex {

    Map<Class, List<SubscribeMethod>> getSubscribeMethodMap();

}
