package com.pq.toolslibrary.pattern.singleton;

/**
 * Created by panqian on 2018/5/8.
 */

public enum  EnumSinglton {
    INSTANCE;

    public EnumSinglton getInstance(){
        return INSTANCE;
    }
}
