package com.clusterhazelcast;

@FunctionalInterface
public interface HZQuorumListener {

    boolean isQuorum();

}
