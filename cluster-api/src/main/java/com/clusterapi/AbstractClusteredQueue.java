package com.clusterapi;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractClusteredQueue<T> implements ClusteredQueue<T> {

    @Override
    public void put(T value) throws InterruptedException {
        queue().put(value);
    }

    @Override
    public int drainTo(List<T> list) {
        return queue().drainTo(list);
    }

    @Override
    public int size() {
        return queue().size();
    }

    @Override
    public T pool(long timeout, TimeUnit unit) throws InterruptedException {
        return queue().poll(timeout,unit);
    }

    protected abstract BlockingQueue<T> queue();
}
