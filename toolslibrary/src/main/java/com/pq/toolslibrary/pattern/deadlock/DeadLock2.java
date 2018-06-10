package com.pq.toolslibrary.pattern.deadlock;

import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock2 extends Thread {
    private final static String tag = "ppp_DeadLock2";

    protected Object tool;

    public static Object fork1 = new Object();

    public static Object fork2 = new Object();

    static ReentrantLock lock1 = new ReentrantLock();

    static ReentrantLock lock2 = new ReentrantLock();

    public DeadLock2(Object obj) {
        this.tool = obj;
        if (tool == fork1) {
            setName("pro A");
        }
        if (tool == fork2) {
            setName("pro B");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            try {
                lock1.lock();
                Log.d(tag, "pro A get fork1");
                Thread.sleep(500);





                boolean suc = lock2.tryLock(3,TimeUnit.SECONDS);
                if (suc){
                    Log.d(tag, "pro A get fork2   -->eating food..for 5 s");
                    Thread.sleep(5*1000);
                }else {
                    Log.d(tag,"pro A cannnot get fork2, quite...");
                }

            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock1.unlock();
//                lock2.unlock();

            }
        }





        if (tool == fork2) {

            try {
                lock2.lock();
                Log.d(tag, "pro B get fork2");
                Thread.sleep(500);


                if (lock1.tryLock(1, TimeUnit.SECONDS)){
                    Log.d(tag, "pro B get fork1   -->eating food..for 5 s");
                    Thread.sleep(5 * 1000);
                }else {
                    Log.d(tag,"pro B cannot get fork1 --> quite");
                }





            } catch (Exception e){
                e.printStackTrace();
            }finally {
//                lock1.unlock();
                lock2.unlock();

            }
        }
    }
}
