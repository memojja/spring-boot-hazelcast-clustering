package com.clusterjvm;

import com.clusterapi.ClusteredLock;
import com.clusterapi.ClustredMap;
import com.clusterapi.DestroyableObject;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class JvmLock implements ClusteredLock {

    private final DestroyableObject destroyableObject;
    private final ReentrantLock reentrantLock;

    public JvmLock(DestroyableObject destroyableObject) {
        this.destroyableObject = destroyableObject;
        reentrantLock = new ReentrantLock();
    }


    @Override
    public void lock() {
        reentrantLock.lock();
    }

    @Override
    public void unlock() {
        reentrantLock.unlock();
    }

    @Override
    public void destroy() {
        destroyableObject.destroy();
    }
}
