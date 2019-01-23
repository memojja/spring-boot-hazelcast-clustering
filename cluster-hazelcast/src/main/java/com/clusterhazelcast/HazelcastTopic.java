package com.clusterhazelcast;

import com.clusterapi.ClusteredTopic;
import com.clusterapi.TopicMessageListener;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.util.List;

public class HazelcastTopic<T> implements ClusteredTopic<T>, MessageListener<T> {

    private final ITopic<T> delegate;
    private final List<TopicMessageListener<T>> topicMessageListeners;

    public HazelcastTopic(ITopic<T> delegate, List<TopicMessageListener<T>> topicMessageListeners) {
        this.delegate = delegate;
        this.topicMessageListeners = topicMessageListeners;
    }

    @Override
    public void register(TopicMessageListener<T> listener) {
        topicMessageListeners.add(listener);
    }

    @Override
    public void unregister(TopicMessageListener<T> listener) {
        topicMessageListeners.remove(listener);
    }

    @Override
    public void publish(T t) {
        delegate.publish(t);
    }

    @Override
    public void destroy() {
        delegate.destroy();
    }

    @Override
    public void onMessage(Message<T> message) {
        topicMessageListeners.forEach( listener -> listener.onMessage(message) );
    }
}
