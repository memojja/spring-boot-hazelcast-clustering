package com.clusterjvm;

import com.clusterapi.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class JvmClusterService implements ClusteringService {

    Map< String,ClustredMap<?,?> > maps = new ConcurrentHashMap<>();
    Map< String,ClusteredQueue<?> > queues = new ConcurrentHashMap<>();
    Map< String,ClusteredTopic<?> > topics = new ConcurrentHashMap<>();
    Map< String,ClusteredAtomicLong >  atomics = new ConcurrentHashMap<>();
    Map< String,ClusteredLock> locks = new ConcurrentHashMap<>();

    Lock lock = new ReentrantLock();

    @Override
    public boolean isLeader() {
        return false;
    }

    @Override
    public <K, V> ClustredMap<K, V> getMap(String id) {
        return (ClustredMap<K, V>) create(id,maps,JvmMap::new);
    }

    @Override
    public <T> ClusteredQueue<T> getQueue(String id) {
        return (ClusteredQueue<T>) create(id,queues,JvmQueue::new);
    }

    @Override
    public <T> ClusteredTopic<T> getReliableTopic(String id) {
        return (ClusteredTopic<T>) create(id,topics,JvmTopic::new);
    }

    @Override
    public ClusteredAtomicLong getAtomicLong(String id) {
        return create(id,atomics,JvmAtomicLong::new);
    }

    @Override
    public ClusteredLock getLock(String id) {
        return create(id,locks,JvmLock::new);
    }

    private <T extends DestroyableObject> T create(
            final String id,
            final Map<String, T> map,
            final Function<DestroyableObject, T> function ){

        try {
            lock.lock();
            final DestroyableObject destroyableObject = map.remove(id);
            return map.computeIfAbsent(id,key -> function.apply(destroyableObject));
        }
        finally {
            lock.unlock();
        }
    }
}
