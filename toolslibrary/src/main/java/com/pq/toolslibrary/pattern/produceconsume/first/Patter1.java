package com.pq.toolslibrary.pattern.produceconsume.first;


import android.util.Log;

/*
   synchronized, wait ,notify
 */
public class Patter1 {

    private static final String TAG="ppp_Pattern1";


    public static void demo(){
        Resource resource = new Resource();
        //生产者线程
        ProducerThread p1 = new ProducerThread(resource,"ProducerThread--1");
        ProducerThread p2 = new ProducerThread(resource,"ProducerThread--2");
        ProducerThread p3 = new ProducerThread(resource,"ProducerThread--3");
        //消费者线程
        ConsumerThread c1 = new ConsumerThread(resource,"ConsumerThread--1");
        ConsumerThread c2 = new ConsumerThread(resource,"ConsumerThread--2");
        ConsumerThread c3 = new ConsumerThread(resource,"ConsumerThread--3");

        p1.start();
        p2.start();
        p3.start();


        c1.start();
        c2.start();
        c3.start();
    }

    /**
     * 公共资源类
     * @author
     *
     */
    static class Resource{//重要
        //当前资源数量
        private volatile int num = 0;
        //资源池中允许存放的资源数目
        private int size = 1;

        /**
         * 从资源池中取走资源
         */
        public synchronized void remove(){
            if(num > 0){
                num--;
                Log.d(TAG,Thread.currentThread().getName() +"\tnofifyAll--before   消费者\t"+
                        "消耗一件资源，" + "当前线程池有" + num + "个");
                notifyAll();//通知生产者生产资源
                Log.d(TAG, Thread.currentThread().getName() +"\tnofifyAll--after   消费者\t"+
                        "消耗一件资源，" + "当前线程池有" + num + "个");
            }else{
                try {
                    //如果没有资源，则消费者进入等待状态
                    Log.d(TAG, Thread.currentThread().getName()+"\twait -- before   消费者\t"
                            + "线程进入等待状态");
                    wait();
                    Log.d(TAG, Thread.currentThread().getName()+ "\twait -- after    消费者\t"
                            + "线程退出等待状态");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * 向资源池中添加资源
         */
        public synchronized void add(){
            if(num < size){
                num++;
                Log.d(TAG,Thread.currentThread().getName() +  "\tnofifyAll--before   生产者\t"
                        + "生产一件资源，当前资源池有"
                        + num + "个");
                //通知等待的消费者
                notifyAll();
                Log.d(TAG,Thread.currentThread().getName() + "\t nofifyAll--after   生产者\t"
                        + "生产一件资源，当前资源池有"
                        + num + "个");
            }else{
                //如果当前资源池中有10件资源
                try{
                    Log.d(TAG,Thread.currentThread().getName() + "\twait -- before    生产者\t"
                            +"线程进入等待");
                    wait();//生产者进入等待状态，并释放锁
                    Log.d(TAG,Thread.currentThread().getName() + "\twait -- after    生产者\t"
                            +"线程退出等待");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 消费者线程
     */
    static class ConsumerThread extends Thread{
        private Resource resource;
        public ConsumerThread(Resource resource){
            this.resource = resource;
        }

        public ConsumerThread(Resource resource,String name){
            super(name);
            this.resource = resource;
        }
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.remove();
            }
        }
    }
    /**
     * 生产者线程
     */
    static class ProducerThread extends Thread{
        private Resource resource;
        public ProducerThread(Resource resource){
            this.resource = resource;
        }
        public ProducerThread(Resource resource, String name){
            super(name);
            this.resource = resource;
        }
        @Override
        public void run() {
            //不断地生产资源
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.add();
            }
        }

    }


}
