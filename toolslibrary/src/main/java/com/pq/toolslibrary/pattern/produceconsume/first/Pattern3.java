package com.pq.toolslibrary.pattern.produceconsume.first;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock 和 Condition解决生产者消费者问题
 */
public class Pattern3 {

    private final static String tag="ppp_Pattern3";

    public static void demo(){
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        Resource resource = new Resource(lock,producerCondition,consumerCondition);

        //生产者线程
        ProducerThread producer1 = new ProducerThread(resource,"ProducerThread--1");
        ProducerThread producer2 = new ProducerThread(resource,"ProducerThread--2");
        ProducerThread producer3 = new ProducerThread(resource,"ProducerThread--3");

        //消费者线程
        ConsumerThread consumer1 = new ConsumerThread(resource,"ConsumerThread--1");
        ConsumerThread consumer2 = new ConsumerThread(resource,"ConsumerThread--2");
        ConsumerThread consumer3 = new ConsumerThread(resource,"ConsumerThread--3");

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
    }


    /**
     * 公共资源类
     * @author tangzhijing
     *
     */
    static class Resource{
        private int num = 0;//当前资源数量
        private int size = 1;//资源池中允许存放的资源数目
        private Lock lock;
        private Condition producerCondition;
        private Condition consumerCondition;
        public Resource(Lock lock, Condition producerCondition, Condition consumerCondition) {
            this.lock = lock;
            this.producerCondition = producerCondition;
            this.consumerCondition = consumerCondition;

        }
        /**
         * 向资源池中添加资源
         */
        public void add(){
            lock.lock();
            try{
                if(num < size){
                    num++;
                    Log.d(tag,Thread.currentThread().getName() +
                            "生产一件资源,当前资源池有" + num + "个"
                    +"\t signal all");
                    //唤醒等待的消费者
                    consumerCondition.signalAll();
                }else{
                    //让生产者线程等待
                    try {
                        Log.d(tag,Thread.currentThread().getName()+"\t"
                        +"await before "+"\t 当前资源池有"+num+   "  线程进入等待");
                        producerCondition.await();
                        Log.d(tag,Thread.currentThread().getName()+"\t"
                                +"await after "+"\t 当前资源池有"+num+   "  线程退出等待");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                lock.unlock();
            }
        }
        /**
         * 从资源池中取走资源
         */
        public void remove(){
            lock.lock();
            try{
                if(num > 0){
                    num--;
                    Log.d(tag,Thread.currentThread().getName() +
                            "消耗一件资源,当前资源池有" + num + "个"
                            +"\t signal all");
                    producerCondition.signalAll();//唤醒等待的生产者
                }else{
                    try {
                        Log.d(tag,Thread.currentThread().getName()+"\t"
                                +"await before "+"\t 当前资源池有"+num+   "  线程进入等待");
                        consumerCondition.await();
                        Log.d(tag,Thread.currentThread().getName()+"\t"
                                +"await after "+"\t 当前资源池有"+num+   "  线程退出等待");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//让消费者等待
                }
            }finally{
                lock.unlock();
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
            //setName("消费者");
        }
        public ConsumerThread(Resource resource , String name){
            super(name);
            this.resource = resource;
        }

        public void run(){
            while(true){
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

        public ProducerThread(Resource resource){
            this.resource = resource;
//            setName("生产者");
        }

        public ProducerThread(Resource resource , String name){
            super(name);
            this.resource = resource;
        }

        public void run(){
            while(true){
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
