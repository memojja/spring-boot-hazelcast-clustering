package com.clusterapi;

import lombok.Value;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Value
public class TopicMessage<T> {

    T value;

    public TopicMessage(final T value){
        super();
        this.value = requireNonNull(value);
    }

}
