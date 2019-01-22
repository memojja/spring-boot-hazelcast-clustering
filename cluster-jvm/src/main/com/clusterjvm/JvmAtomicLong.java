package com.clusterjvm;

import com.clusterapi.ClusteredAtomicLong;
import com.clusterapi.DestroyableObject;

import java.util.concurrent.atomic.AtomicLong;

public class JvmAtomicLong implements ClusteredAtomicLong {

    private final DestroyableObject destroyableObject;
    private final AtomicLong atomicLong;

    public JvmAtomicLong(DestroyableObject destroyableObject) {
        this.destroyableObject = destroyableObject;
        this.atomicLong = new AtomicLong();
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
        destroyableObject.destroy();
    }
}
