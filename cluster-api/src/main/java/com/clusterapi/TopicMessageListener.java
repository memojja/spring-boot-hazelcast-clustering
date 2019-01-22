package com.clusterapi;

@FunctionalInterface
public interface TopicMessageListener<T> {

    void onMessage(Object message);

}
