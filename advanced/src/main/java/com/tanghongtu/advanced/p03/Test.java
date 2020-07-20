package com.tanghongtu.advanced.p03;

import java.io.Serializable;

/**
 * Created by tanghongtu on 2020/7/20.
 */
public class Test implements Serializable, Cloneable {

    private int num = 1;

    public int add(int i) {
        int j = 10;
        num = num + 1;
        return num;
    }

}
