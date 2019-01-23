package com.clusterhazelcast;

import com.hazelcast.core.Cluster;
import com.hazelcast.internal.cluster.ClusterService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.boot.actuate.health.Status.OUT_OF_SERVICE;
import static org.springframework.boot.actuate.health.Status.UP;

//@AllArgsConstructor(access= PACKAGE)
//@FieldDefaults(level=PRIVATE, makeFinal=true)
public class HazelcastHealthIndicator extends AbstractHealthIndicator {

    private final ClusterService clusterService;
    private final HZQuorumListener hzQuorumListener;
    private final Cluster cluster;

    public HazelcastHealthIndicator(ClusterService clusterService, HZQuorumListener hzQuorumListener, Cluster cluster) {
        this.clusterService = clusterService;
        this.hzQuorumListener = hzQuorumListener;
        this.cluster = cluster;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.withDetail("isLeader", clusterService.isMaster());
        builder.withDetail("isQuorum", hzQuorumListener.isQuorum());
        builder.withDetail("members", cluster.getMembers().toString());
        builder.status(hzQuorumListener.isQuorum() ? UP : OUT_OF_SERVICE);    }
}
