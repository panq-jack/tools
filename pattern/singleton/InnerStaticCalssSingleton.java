package com.pq.toolslibrary.pattern.singleton;

/**
 * Created by panqian on 2018/5/8.
 */

public class InnerStaticCalssSingleton {

    private InnerStaticCalssSingleton(){

    }

    public static InnerStaticCalssSingleton getInstance(){
        return InnerClass.Instance;
    }
    private static class InnerClass{
        private final static InnerStaticCalssSingleton Instance = new InnerStaticCalssSingleton();
    }
}
