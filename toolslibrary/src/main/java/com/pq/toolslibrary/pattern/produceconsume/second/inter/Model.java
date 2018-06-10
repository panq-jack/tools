package com.pq.toolslibrary.pattern.produceconsume.second.inter;

public interface Model {
    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();
}
