package com.clusterjvm;

import com.clusterapi.ClusteredQueue;
import com.clusterapi.DestroyableObject;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class JvmQueue implements ClusteredQueue {

    private final DestroyableObject destroyableObject;
    private final BlockingQueue blockingQueue;

    public JvmQueue(DestroyableObject destroyableObject) {
        this.destroyableObject = destroyableObject;
        blockingQueue = new LinkedBlockingDeque();
    }

    @Override
    public void put(Object value) throws InterruptedException {
        blockingQueue.put(value);
    }

    @Override
    public int drainTo(List list) {
        return blockingQueue.drainTo(list);
    }

    @Override
    public int size() {
        return blockingQueue.size();
    }

    @Override
    public Object pool(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
