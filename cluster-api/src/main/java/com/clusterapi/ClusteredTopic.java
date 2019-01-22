package com.clusterapi;

public interface ClusteredTopic<T> extends DestroyableObject {

    void register(TopicMessageListener<T> listener);
    void unregister(TopicMessageListener<T> listener);
    void publish(T t);
}
