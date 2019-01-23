package com.clusterhazelcast;

import com.clusterapi.ClusteredAtomicLong;
import com.hazelcast.core.IAtomicLong;

public class HazelcastAtomicLong implements ClusteredAtomicLong {

    private final IAtomicLong atomicLong;

    public HazelcastAtomicLong(IAtomicLong atomicLong) {
        this.atomicLong = atomicLong;
    }

    @Override
    public long increament() {
        return atomicLong.incrementAndGet();
    }

    @Override
    public long get() {
        return atomicLong.get();
    }

    @Override
    public void destroy() {
        atomicLong.destroy();
    }
}
