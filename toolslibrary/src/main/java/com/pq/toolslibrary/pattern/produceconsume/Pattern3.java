package com.pq.toolslibrary.pattern.produceconsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock 和 Condition解决生产者消费者问题
 */
public class Pattern3 {

    public static void demo(){
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        Resource resource = new Resource(lock,producerCondition,consumerCondition);

        //生产者线程
        ProducerThread producer1 = new ProducerThread(resource);
        ProducerThread producer2 = new ProducerThread(resource);
        ProducerThread producer3 = new ProducerThread(resource);

        //消费者线程
        ConsumerThread consumer1 = new ConsumerThread(resource);
        ConsumerThread consumer2 = new ConsumerThread(resource);
        ConsumerThread consumer3 = new ConsumerThread(resource);

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
        private int size = 10;//资源池中允许存放的资源数目
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
                    System.out.println(Thread.currentThread().getName() +
                            "生产一件资源,当前资源池有" + num + "个");
                    //唤醒等待的消费者
                    consumerCondition.signalAll();
                }else{
                    //让生产者线程等待
                    try {
                        producerCondition.await();
                        System.out.println(Thread.currentThread().getName() + "线程进入等待");
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
                    System.out.println("消费者" + Thread.currentThread().getName()
                            + "消耗一件资源," + "当前资源池有" + num + "个");
                    producerCondition.signalAll();//唤醒等待的生产者
                }else{
                    try {
                        consumerCondition.await();
                        System.out.println(Thread.currentThread().getName() + "线程进入等待");
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
