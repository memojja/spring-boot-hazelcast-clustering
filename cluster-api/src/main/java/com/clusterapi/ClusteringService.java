package com.clusterapi;

import org.springframework.stereotype.Component;

@Component
public interface ClusteringService {

    boolean isLeader();

    <K,V> ClustredMap<K,V> getMap(String id);

    <T> ClusteredQueue<T> getQueue(String id);

    <T> ClusteredTopic<T> getReliableTopic(String id);

    ClusteredAtomicLong getAtomicLong(String id);

    ClusteredLock getLock(String id);

}
