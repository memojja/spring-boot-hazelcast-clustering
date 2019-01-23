package com.clusterhazelcast;

import com.clusterapi.AbstractClusteredQueue;
import com.clusterapi.ClusteredQueue;
import com.hazelcast.core.IQueue;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class HazelcastQueue<T> extends AbstractClusteredQueue<T> {

    private final IQueue queue;

    public HazelcastQueue(IQueue queue) {
        this.queue = queue;
    }


    @Override
    protected BlockingQueue<T> queue() {
        return queue;
    }

    @Override
    public void destroy() {
        queue.destroy();
    }
}
