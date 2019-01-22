package com.clusterjvm;

import com.clusterapi.ClusteredTopic;
import com.clusterapi.DestroyableObject;
import com.clusterapi.TopicMessage;
import com.clusterapi.TopicMessageListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JvmTopic<T> implements ClusteredTopic<T> {

    private final DestroyableObject destroyableObject;
    private List< TopicMessageListener <T> > topicMessageListeners;

    public JvmTopic(DestroyableObject destroyableObject) {
        this.destroyableObject = destroyableObject;
        topicMessageListeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public void register(TopicMessageListener listener) {
        topicMessageListeners.add(listener);
    }

    @Override
    public void unregister(TopicMessageListener listener) {
        topicMessageListeners.remove(listener);
    }

    @Override
    public void publish(Object o) {
        final TopicMessage<T> message  = new TopicMessage<T>((T) o);
        topicMessageListeners.forEach( listener -> listener.onMessage(o)  );

    }

    @Override
    public void destroy() {
        destroyableObject.destroy();
    }
}
