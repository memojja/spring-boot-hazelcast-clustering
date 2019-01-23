package com.clusterhazelcast;

import com.clusterapi.ClustredMap;
import com.hazelcast.core.IMap;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class HazelcastMap<K,V> implements ClustredMap<K,V> {

    private final IMap<K,V> map;

    public HazelcastMap(IMap<K, V> map) {
        this.map = map;
    }

    @Override
    public Optional<String> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public void put(K key, V value) {
        map.put(key,value);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public Map<K, V> copyOf() {
        return new Hashtable<>(map);
    }

    @Override
    public void destroy() {
        map.destroy();
    }
}
