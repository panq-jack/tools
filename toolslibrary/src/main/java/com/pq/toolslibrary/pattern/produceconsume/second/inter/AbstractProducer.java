package com.pq.toolslibrary.pattern.produceconsume.second.inter;

public abstract class AbstractProducer implements Producer , Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}
