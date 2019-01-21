package com.clusterapi;

public interface ClusteredLock extends DeployableObject {

    void lock();

    void unlock();
}
