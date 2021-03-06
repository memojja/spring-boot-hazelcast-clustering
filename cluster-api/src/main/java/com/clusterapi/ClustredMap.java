package com.clusterapi;

import java.util.Map;
import java.util.Optional;

public interface ClustredMap<K,V> extends DestroyableObject {

    Optional<String> get(final K key);

    void put(final K key, final  V value);

    void remove(final K key);

    Map<K,V> copyOf();
}
