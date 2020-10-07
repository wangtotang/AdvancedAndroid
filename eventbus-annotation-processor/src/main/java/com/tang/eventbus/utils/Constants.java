package com.tang.eventbus.utils;

public class Constants {

    //注解处理器支持的注解类型
    public static final String SUBSCRIBE_ANNOTATION_TYPES = "com.tang.eventbus.Subscribe";

    //所有的事件订阅方法，生成索引接口
    public static final String SUBSCRIBE_INDEX = "com.tang.eventbus.SubscribeIndex";

    //注解处理器的参数名
    public static final String OPTION_EVENT_BUS_INDEX = "eventBusIndex";

    //全局属性名
    public static final String CACHE_MAP = "CACHE_MAP";

    //局部属性名
    public static final String METHODS = "methods";

    //获取所有订阅方法的方法名
    public static final String GET_SUBSCRIBE_METHOD_NAME = "getSubscribeMethodMap";

}
