package com.pq.toolslibrary.pattern.produceconsume.first;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * blockingqueue  实现
 */
public class Pattern2 {

    private final static String tag = "ppp_Pattern2";



    public static void demo(){
        
        Resource resource = new Resource();
        
        //生产者线程
        ProducerThread p1 = new ProducerThread(resource,"producerThread---1");
        ProducerThread p2 = new ProducerThread(resource,"producerThread---2");
        ProducerThread p3 = new ProducerThread(resource,"producerThread---3");
        //多个消费者
        ConsumerThread c1 = new ConsumerThread(resource, "ConsumerThread---1");
        ConsumerThread c2 = new ConsumerThread(resource ,"ConsumerThread---2");
        ConsumerThread c3 = new ConsumerThread(resource ,"ConsumerThread---3");

        p1.start();
        p2.start();
        p3.start();

        c1.start();
        c2.start();
        c3.start();
    }

    static class Resource{
        private BlockingQueue resourceQueue = new LinkedBlockingQueue(1);
        /**
         * 向资源池中添加资源
         */
        public void add(){
            try {
                Log.d(tag,Thread.currentThread().getName()+ "put -- before  "+"\t生产者"
                        + "生产一件资源," + "当前资源池有" + resourceQueue.size() +
                        "个资源");
                resourceQueue.put(1);

                Log.d(tag, Thread.currentThread().getName()+ "put -- after  "+ "\t生产者"
                        + "生产一件资源," + "当前资源池有" + resourceQueue.size() +
                        "个资源");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * 向资源池中移除资源
         */
        public void remove(){
            try {
                Log.d(tag,Thread.currentThread().getName()+ "take -- before  "+"\t消费者"
                        + "消耗一件资源," + "当前资源池有" + resourceQueue.size() +
                        "个资源");

                resourceQueue.take();
                Log.d(tag,Thread.currentThread().getName()+ "take -- after  "+"\t消费者"
                        + "消耗一件资源," + "当前资源池有" + resourceQueue.size() +
                        "个资源");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 消费者线程
     * @author 
     *
     */
    static class ConsumerThread extends Thread {
        private Resource resource;

        public ConsumerThread(Resource resource) {
            this.resource = resource;
//            setName("消费者");
        }

        public ConsumerThread(Resource resource, String name){
            super(name);
            this.resource = resource;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep((long) (1000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.remove();
            }
        }
    }
    /**
     * 生产者线程
     * @author tangzhijing
     *
     */
    static class ProducerThread extends Thread{
        private Resource resource;
        public ProducerThread(Resource resource) {
            this.resource = resource;
            //setName("生产者");
        }
        public ProducerThread(Resource resource,String name){
            super(name);
            this.resource = resource;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep((long) (1000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.add();
            }
        }
    }
    
    
    
}
