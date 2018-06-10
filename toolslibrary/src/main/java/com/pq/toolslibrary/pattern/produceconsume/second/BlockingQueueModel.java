package com.pq.toolslibrary.pattern.produceconsume.second;

import android.util.Log;

import com.pq.toolslibrary.pattern.produceconsume.second.inter.AbstractConsumer;
import com.pq.toolslibrary.pattern.produceconsume.second.inter.AbstractProducer;
import com.pq.toolslibrary.pattern.produceconsume.second.inter.Consumer;
import com.pq.toolslibrary.pattern.produceconsume.second.inter.Model;
import com.pq.toolslibrary.pattern.produceconsume.second.inter.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {
    private final static String TAG = "BlockingQueueModel";

    private final BlockingQueue<Task> queue;

    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        // LinkedBlockingQueue 的队列是 lazy-init 的，但 ArrayBlockingQueue 在创建时就已经 init
        this.queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {
        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            // 固定时间范围的消费，模拟相对稳定的服务器处理过程
            Thread.sleep(500 + (long) (Math.random() * 500));
            Log.d(TAG,"consume: " + task.no);
        }
    }

    private class ProducerImpl extends AbstractProducer implements Producer, Runnable {
        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            Log.d(TAG,"produce: " + task.no);
            queue.put(task);
        }
    }

    public void demo(){
        Model model = new BlockingQueueModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableProducer()).start();
        }

    }

}
