package com.clusterhazelcast;

import com.clusterapi.*;
import com.google.common.collect.Iterables;
import com.hazelcast.core.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access=PACKAGE)
@FieldDefaults(level=PRIVATE, makeFinal=true)
public class HazelcastClusteringService implements ClusteringService {

    public static final String MAP = "map";
    @NonNull
    HazelcastInstance hz;

    @NonNull
    HZQuorumListener quorum;

    public HazelcastClusteringService(HazelcastInstance hz, HZQuorumListener listener) {
        this.hz = hz;
    }


    @Override
    public boolean isLeader() {
        final Cluster cluster = hz.getCluster();
        final Member leader = Iterables.getFirst( cluster.getMembers(),cluster.getLocalMember()  );
        return leader.isLiteMember() && quorum.isQuorum();
    }

    @Override
    public <K, V> ClustredMap<K, V> getMap(String id) {
        IMap<K, V> map =  hz.getMap(MAP);
        return new HazelcastMap<>(map);
    }

    @Override
    public <T> ClusteredQueue<T> getQueue(String id) {
        IQueue<T> queue = hz.getQueue(MAP);
        return new HazelcastQueue<>(queue);
    }

    @Override
    public <T> ClusteredTopic<T> getReliableTopic(String id) {
        ITopic<T> topic = hz.getTopic(MAP);
        return new HazelcastTopic<>(topic);
    }

    @Override
    public ClusteredAtomicLong getAtomicLong(String id) {
        IAtomicLong atomicLong = hz.getAtomicLong(MAP);
        return new HazelcastAtomicLong(atomicLong);
    }

    @Override
    public ClusteredLock getLock(String id) {
        ILock iLock = hz.getLock(MAP);
        return new HazelcastLock(iLock);
    }
}
