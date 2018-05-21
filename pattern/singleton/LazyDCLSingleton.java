package com.pq.toolslibrary.pattern.singleton;

import android.util.Log;

/**
 * Created by panqian on 2018/5/8.
 * dcl 懒汉式  double check lock 双重检测锁
 */

public class LazyDCLSingleton {

    private static volatile LazyDCLSingleton instance = null;

    private LazyDCLSingleton(){
        Log.d("LazyDCLSingleton","LazyDCLSingleton construct");
    }

    public static LazyDCLSingleton getInstance(){
        if (null == instance){
            synchronized (LazyDCLSingleton.class){
                if (null == instance){
                    instance = new LazyDCLSingleton();
                }
            }
        }
        return instance;
    }

    public static void test(){
        Log.d("LazyDCLSingleton","just invoke ");
    }
}
