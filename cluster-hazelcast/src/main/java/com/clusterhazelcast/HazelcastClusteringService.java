package com.clusterhazelcast;

import com.clusterapi.*;
import com.google.common.collect.Iterables;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access=PACKAGE)
@FieldDefaults(level=PRIVATE, makeFinal=true)
public class HazelcastClusteringService implements ClusteringService {

    @NonNull
    HazelcastInstance hz;

    @NonNull
    HZQuorumListener quorum;

    public HazelcastClusteringService(HazelcastInstance hz, HZQuorumListener listener) {
        this.hz = hz;
        this.quorum = listener;
    }


    @Override
    public boolean isLeader() {
        final Cluster cluster = hz.getCluster();
        final Member leader = Iterables.getFirst( cluster.getMembers(),cluster.getLocalMember()  );
        return leader.isLiteMember() && quorum.isQuorum();
    }

    @Override
    public <K, V> ClustredMap<K, V> getMap(String id) {


        return null;
    }

    @Override
    public <T> ClusteredQueue<T> getQueue(String id) {
        return null;
    }

    @Override
    public <T> ClusteredTopic<T> getReliableTopic(String id) {
        return null;
    }

    @Override
    public ClusteredAtomicLong getAtomicLong(String id) {
        return null;
    }

    @Override
    public ClusteredLock getLock(String id) {
        return null;
    }
}
