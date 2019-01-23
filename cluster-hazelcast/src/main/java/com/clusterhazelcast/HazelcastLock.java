package com.clusterhazelcast;

import com.clusterapi.ClusteredLock;
import com.hazelcast.core.ILock;

public class HazelcastLock implements ClusteredLock {

    private final ILock iLock;

    public HazelcastLock(ILock iLock) {
        this.iLock = iLock;
    }

    @Override
    public void lock() {
        iLock.lock();
    }

    @Override
    public void unlock() {
        iLock.unlock();
    }

    @Override
    public void destroy() {
        iLock.destroy();
    }
}
