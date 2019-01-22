package com.clusterapi;

public interface ClusteredLock extends DestroyableObject {

    void lock();

    void unlock();
}
