package com.pq.toolslibrary.pattern.singleton;

import android.util.Log;

/**
 * Created by panqian on 2018/5/8.
 * 懒汉式  synchronize 效率较低
 */

public class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton(){
        Log.d("LazySingleton","LazySingleton construct");

    }

    public static synchronized LazySingleton getInstance(){
        if (null == instance){
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void test(){
        Log.d("LazySingleton","just invoke ");
    }
}
