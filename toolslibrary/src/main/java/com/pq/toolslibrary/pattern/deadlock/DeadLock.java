package com.pq.toolslibrary.pattern.deadlock;

import android.util.Log;

public class DeadLock extends Thread{
    private final static String tag="ppp_DeadLock";

    protected Object tool;

    public static Object fork1 = new Object();

    public static Object fork2 = new Object();

    public DeadLock(Object obj){
        this.tool = obj;
        if (tool == fork1){
            setName("pro A");
        }
        if (tool == fork2){
            setName("pro B");
        }
    }

    @Override
    public void run() {
        if (tool == fork1){
            synchronized (fork1){
                Log.d(tag,"pro A get fork1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork2){
                    Log.d(tag,"pro A get fork2   -->eating food..");
                }
            }
        }

        if (tool == fork2){
            synchronized (fork2){
                Log.d(tag,"pro B get fork2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork1){
                    Log.d(tag,"pro B get fork1   -->eating food..");
                }
            }
        }
    }
}
