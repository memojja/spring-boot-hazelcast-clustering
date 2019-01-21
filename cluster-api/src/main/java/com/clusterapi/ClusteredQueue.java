package com.clusterapi;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface ClusteredQueue<T> extends DeployableObject {

    void put(T value) throws InterruptedException;

    int drainTo(List<T> list);

    int size();

    T pool(long timeout, TimeUnit unit) throws InterruptedException;
}
