package com.clusterapi;

public interface ClusteredAtomicLong extends DestroyableObject {

    long increament();

    long get();
}
