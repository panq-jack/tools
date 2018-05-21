package com.pq.toolslibrary.pattern.singleton;

import android.util.Log;

/**
 * Created by panqian on 2018/5/8.
 * 饿汉式
 *
 */

public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
        Log.d("HungrySingleton","HungrySingleton init");
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

    public static void test(){
        Log.d("HungrySingleton","just invoke ");
    }
}
