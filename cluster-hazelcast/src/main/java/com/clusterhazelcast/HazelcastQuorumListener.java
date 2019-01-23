package com.clusterhazelcast;

import com.hazelcast.core.MembershipAdapter;
import com.hazelcast.core.MembershipEvent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.atomic.AtomicBoolean;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HazelcastQuorumListener extends MembershipAdapter implements HZQuorumListener {

    int quorum;
    AtomicBoolean isQuorum;

    public HazelcastQuorumListener(int quorum) {
        this.quorum = quorum;
    }

    @Override
    public boolean isQuorum() {
        return isQuorum.get();
    }

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        super.memberAdded(membershipEvent);
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        isQuorum.set(membershipEvent.getMembers().size() >= quorum);
//        super.memberRemoved(membershipEvent);
    }
}
